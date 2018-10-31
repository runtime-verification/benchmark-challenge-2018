#include "array"
#include "discrete_time.hpp"

template<typename T = int>
struct MonitorAbsentAQ {

	std::array<bool,3> states = std::array<bool,3>{0,0,0};
	std::array<bool,3> states_pre = std::array<bool,3>{0,0,0};

	std::array<interval_set<T>,1> tstates = std::array<interval_set<T>,1>{interval_set<T>()};
	std::array<interval_set<T>,1> tstates_pre = std::array<interval_set<T>,1>{interval_set<T>()};

	T now = 0;

	bool update(bool p, bool q){

		now = now + 1;
		states_pre = states;
		tstates_pre = tstates;

		tstates[0] = update_timed_since<T>(tstates[0], now, true, q, 0, 10);
		states[0] = not(p);
		states[1] = q or (states[0] and states_pre[1]);
		states[2] = (states[1] or not(output_timed_since<T>(tstates[0], now)));

		return output();
	}

	bool output(){
		return states[2];
	}
};