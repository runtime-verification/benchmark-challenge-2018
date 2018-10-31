#include "array"
#include "discrete_time.hpp"

template<typename T = int>
struct MonitorRecurBQR {

	std::array<bool,7> states = std::array<bool,7>{0,0,0,0,0,0,0};
	std::array<bool,7> states_pre = std::array<bool,7>{0,0,0,0,0,0,0};

	std::array<interval_set<T>,1> tstates = std::array<interval_set<T>,1>{interval_set<T>()};
	std::array<interval_set<T>,1> tstates_pre = std::array<interval_set<T>,1>{interval_set<T>()};

	T now = 0;

	bool update(bool p, bool q, bool r){

		now = now + 1;
		states_pre = states;
		tstates_pre = tstates;

		states[0] = not(q);
		states[1] = (states[0] and r);
		states[2] = (states_pre[2] or q);
		states[3] = (states[1] and states[2]);
		states[4] = (q or p);
		tstates[0] = update_timed_since<T>(tstates[0], now, true, states[4], 0, 10);
		states[5] = q or (output_timed_since<T>(tstates[0], now) and states_pre[5]);
		states[6] = (not(states[3]) or states[5]);

		return output();
	}

	bool output(){
		return states[6];
	}
};