#include "array"
#include "discrete_time.hpp"

template<typename T = int>
struct MonitorAbsentBR {

	std::array<bool,2> states = std::array<bool,2>{0,0};
	std::array<bool,2> states_pre = std::array<bool,2>{0,0};

	std::array<interval_set<T>,1> tstates = std::array<interval_set<T>,1>{interval_set<T>()};
	std::array<interval_set<T>,1> tstates_pre = std::array<interval_set<T>,1>{interval_set<T>()};

	T now = 0;

	bool update(bool p, bool r){

		now = now + 1;
		states_pre = states;
		tstates_pre = tstates;

		states[0] = not(p);
		tstates[0] = update_timed_since<T>(tstates[0], now, true, not(states[0]), 0, 10);
		states[1] = (not(r) or not(output_timed_since<T>(tstates[0], now)));

		return output();
	}

	bool output(){
		return states[1];
	}
};