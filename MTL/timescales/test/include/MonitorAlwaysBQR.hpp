#include "array"
#include "discrete_time.hpp"

template<typename T = int>
struct MonitorAlwaysBQR {

	std::array<bool,5> states = std::array<bool,5>{0,0,0,0,0};
	std::array<bool,5> states_pre = std::array<bool,5>{0,0,0,0,0};

	std::array<interval_set<T>,1> tstates = std::array<interval_set<T>,1>{interval_set<T>()};
	std::array<interval_set<T>,1> tstates_pre = std::array<interval_set<T>,1>{interval_set<T>()};

	T now = 0;

	bool update(bool p, bool q, bool r){

		now = now + 1;
		states_pre = states;
		tstates_pre = tstates;

		states[0] = not(q);
		states[1] = (r and states[0]);
		states[2] = (states_pre[2] or q);
		states[3] = (states[1] and states[2]);
		tstates[0] = update_timed_since<T>(tstates[0], now, p, q, 3, 10);
		states[4] = (output_timed_since<T>(tstates[0], now) or not(states[3]));

		return output();
	}

	bool output(){
		return states[4];
	}
};