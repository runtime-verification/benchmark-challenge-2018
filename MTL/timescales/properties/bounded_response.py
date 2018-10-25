import random

from collections import namedtuple

TimedData = namedtuple('TimedData', ["time", 'data'])

class bounded_response_globally:

	# @staticmethod
	# def generate_future_formula(upper_bound):
	# 	  return "always(p -> eventually[{a},{b}] s)".format(a=lower_bound, b=upper_bound)

	@staticmethod
	def generate_formula(lower_bound, upper_bound):
		return "(s -> once[{a},{b}] p) and not( not(s) since[>={b}] p)".format(a=lower_bound, b=upper_bound)


	@staticmethod
	def generate_trace(lower_bound, upper_bound, duration=1000, failing_end=True):

		Data = namedtuple('Data', ['p', 's'])

		time = 1
		rows = []

		while time < duration:

			rows += [TimedData(time=time, data=Data(p=1, s=0))]
			time += 1

			k = random.randint(lower_bound+1, upper_bound) # Draws a random number between lower and upper bounds

			for j in range(k-1):
				rows += [TimedData(time=time, data=Data(p=0, s=0))]
				time += 1

			rows += [TimedData(time=time, data=Data(p=0, s=1))]
			time += 1

		if failing_end:
			
			rows += [TimedData(time=time, data=Data(p=1,s=0))]
			time += 1
			for j in range(upper_bound):
				rows += [TimedData(time=time, data=Data(p=0,s=0))]
				time += 1

		return rows

class bounded_response_between_q_and_r:

	# @staticmethod
	# def generate_future_formula(upper_bound):
	# 	  return "always(p -> eventually[{a},{b}] q)".format(a=lower_bound, b=upper_bound)

	@staticmethod
	def generate_formula(lower_bound, upper_bound):
		# return "(r && !q && once q) -> (s -> (not q since (p and not q))) since q)".format(a=lower_bound, b=upper_bound)
		# return "(r && !q && once q) -> (   (s -> (not(q) since[{a},{b}](p and not q))) since q)".format(a=lower_bound, b=upper_bound)
		return "(r && !q && once q) -> ( ((s -> once[{a},{b}] p) and not( not(s) since[>={b}] p)) since[>=0] q)".format(a=lower_bound, b=upper_bound)


	@staticmethod
	def generate_trace(lower_bound, upper_bound, duration=1000, failing_end=True):

		Data = namedtuple('Data', ['q', 'p', 's', 'r'])

		time = 1
		rows = []

		while time < duration:

			rows += [TimedData(time=time, data=Data(q=1, p=0, s=0, r=0))]
			time += 1

			rows += [TimedData(time=time, data=Data(q=0, p=0, s=0, r=0))]
			time += 1

			rows += [TimedData(time=time, data=Data(q=0, p=1, s=0, r=0))]
			time += 1

			k = random.randint(lower_bound+1, upper_bound) # Draws a random number between lower and upper bounds

			for j in range(k-1):
				rows += [TimedData(time=time, data=Data(q=0, p=0, s=0, r=0))]
				time += 1

			rows += [TimedData(time=time, data=Data(q=0, p=0, s=1, r=0))]
			time += 1

			rows += [TimedData(time=time, data=Data(q=0, p=0, s=0, r=0))]
			time += 1

			rows += [TimedData(time=time, data=Data(q=0, p=0, s=0, r=1))]
			time += 1

		if failing_end:

			rows += [TimedData(time=time, data=Data(q=1, p=0, s=0, r=0))]
			time += 1

			rows += [TimedData(time=time, data=Data(q=0, p=1, s=0, r=0))]
			time += 1

			for j in range(upper_bound+1):
				rows += [TimedData(time=time, data=Data(q=0, p=0, s=0, r=0))]
				time += 1

			rows += [TimedData(time=time, data=Data(q=0, p=0, s=0, r=1))]
			time += 1

		return rows