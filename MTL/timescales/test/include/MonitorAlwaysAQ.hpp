#include "array"
#include "discrete_time.hpp"

template<typename T = int>
struct MonitorAlwaysAQ {

	std::array<bool,2> states = std::array<bool,2>{0,0};
	std::array<bool,2> states_pre = std::array<bool,2>{0,0};

	std::array<interval_set<T>,1> tstates = std::array<interval_set<T>,1>{interval_set<T>()};
	std::array<interval_set<T>,1> tstates_pre = std::array<interval_set<T>,1>{interval_set<T>()};

	T now = 0;

	bool update(bool p, bool q){

		now = now + 1;
		states_pre = states;
		tstates_pre = tstates;

		tstates[0] = update_timed_since<T>(tstates[0], now, true, q, 0, 10);
		states[0] = q or (p and states_pre[0]);
		states[1] = (not(output_timed_since<T>(tstates[0], now)) or states[0]);

		return output();
	}

	bool output(){
		return states[1];
	}
};