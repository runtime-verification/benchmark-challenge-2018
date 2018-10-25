import random
from collections import namedtuple

TimedData = namedtuple('TimedData', ["time", 'data'])

class challenge_pandq:

	@staticmethod
	def generate_formula(lower_bound, upper_bound):
		return "(once[>={a}] p) -> (p since[{a},{b}] q)".format(a=lower_bound, b=upper_bound)


	@staticmethod
	def generate_trace(lower_bound, upper_bound, duration=1000, failing_end=True):

		Data = namedtuple('Data', ['p', 'q'])

		time = 1
		rows = []

		while time < duration:

			rows += [TimedData(time=time, data=Data(p=1, q=1))]
			time += 1

		if failing_end:
			
			rows += [TimedData(time=time, data=Data(p=0,q=0))]
			time += 1

		return rows
