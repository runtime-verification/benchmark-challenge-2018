import os
import sys
import csv

from collections import namedtuple

from properties.bounded_absence import *
from properties.bounded_universality import *
from properties.bounded_recurrence import *
from properties.bounded_response import *

from properties.challenge_pandq import *
from properties.challenge_delay import *

def eliminate_stuttering(rows, cap=None):

	if cap == None:
		cap = sys.maxsize

	prev = rows[0]
	nrows = []

	for row in rows[1:-1]:
		if (row.data != prev.data) or (prev.time - nrows[-1].time >= cap):
			nrows += [prev]
		prev = row
	
	nrows += [rows[-1]]

	return nrows

def write_spec(spec, filename, directory=""):
	spec ='''---
name : "{name}"
pattern : "{spec}"
'''.format(name=filename, spec=spec)

	with open(os.path.join(directory, '{filename}.yaml'.format(filename=filename)), 'w') as f:
		f.write(spec)
		
def write_trace(rows, filename, directory=""):

	with open(os.path.join(directory, '{filename}.csv'.format(filename=filename)), 'w') as f:
		w = csv.writer(f)
		w.writerows([["time"] + [f for f in rows[0].data._fields]])
		w.writerows([[row.time] + [d for d in row.data] for row in rows])

def generate(property, lower_bound, upper_bound, min_recur, max_recur, duration, limit_stutter, failing_end):

	spec = ""
	rows = []

	if property == "absence_after_q":
		spec = bounded_absence_after_q.generate_formula(upper_bound)
		rows = bounded_absence_after_q.generate_trace(upper_bound, duration, failing_end)
	elif property == "absence_before_r":
		spec = bounded_absence_before_r.generate_formula(upper_bound)
		rows = bounded_absence_before_r.generate_trace(upper_bound, duration, failing_end)
	elif property == "absence_between_q_and_r":
		spec = bounded_absence_between_q_and_r.generate_formula(lower_bound, upper_bound)
		rows = bounded_absence_between_q_and_r.generate_trace(lower_bound, upper_bound, duration, failing_end)
	elif property == "always_after_q":
		spec = bounded_universality_after_q.generate_formula(upper_bound)
		rows = bounded_universality_after_q.generate_trace(upper_bound, duration, failing_end)
	elif property == "always_before_r":
		spec = bounded_universality_before_r.generate_formula(upper_bound)
		rows = bounded_universality_before_r.generate_trace(upper_bound, duration, failing_end)
	elif property == "always_between_q_and_r":
		spec = bounded_universality_between_q_and_r.generate_formula(lower_bound, upper_bound)
		rows = bounded_universality_between_q_and_r.generate_trace(lower_bound, upper_bound, duration, failing_end)
	elif property == "recurrence_globally":
		spec = bounded_recurrence_globally.generate_formula(upper_bound)
		rows = bounded_recurrence_globally.generate_trace(upper_bound, duration, failing_end)
	elif property == "recurrence_between_q_and_r":
		spec = bounded_recurrence_between_q_and_r.generate_formula(upper_bound)
		rows = bounded_recurrence_between_q_and_r.generate_trace(upper_bound, duration, min_recur, max_recur, failing_end)
	elif property == "response_globally":
		spec = bounded_response_globally.generate_formula(lower_bound, upper_bound)
		rows = bounded_response_globally.generate_trace(lower_bound, upper_bound, duration, failing_end)
	elif property == "response_between_q_and_r":
		spec = bounded_response_between_q_and_r.generate_formula(lower_bound, upper_bound)
		rows = bounded_response_between_q_and_r.generate_trace(lower_bound, upper_bound, duration, failing_end)
	elif property == "challenge_pandq":
		spec = challenge_pandq.generate_formula(lower_bound, upper_bound)
		rows = challenge_pandq.generate_trace(lower_bound, upper_bound, duration, failing_end)
	elif property == "challenge_delay":
		spec = challenge_delay.generate_formula(lower_bound, upper_bound)
		rows = challenge_delay.generate_trace(lower_bound, upper_bound, duration, failing_end)
	else:
		print("Unknown property. Please see the help.")

	rows = rows if limit_stutter == 0 else eliminate_stuttering(rows, limit_stutter)

	return spec, rows
