#include "array"
#include "discrete_time.hpp"

template<typename T = int>
struct MonitorRespondBQR {

	std::array<bool,10> states = std::array<bool,10>{0,0,0,0,0,0,0,0,0,0};
	std::array<bool,10> states_pre = std::array<bool,10>{0,0,0,0,0,0,0,0,0,0};

	std::array<interval_set<T>,2> tstates = std::array<interval_set<T>,2>{interval_set<T>(),interval_set<T>()};
	std::array<interval_set<T>,2> tstates_pre = std::array<interval_set<T>,2>{interval_set<T>(),interval_set<T>()};

	T now = 0;

	bool update(bool p, bool q, bool r, bool s){

		now = now + 1;
		states_pre = states;
		tstates_pre = tstates;

		states[0] = not(q);
		states[1] = (states[0] and r);
		states[2] = (states_pre[2] or q);
		states[3] = (states[1] and states[2]);
		tstates[0] = update_timed_since<T>(tstates[0], now, true, p, 3, 10);
		states[4] = (not(s) or output_timed_since<T>(tstates[0], now));
		states[5] = not(s);
		tstates[1] = update_timed_since_unbounded<T>(tstates[1], now, states[5], p, 10);
		states[6] = not(output_timed_since<T>(tstates[1], now));
		states[7] = (states[4] and states[6]);
		states[8] = q or (states[7] and states_pre[8]);
		states[9] = (not(states[3]) or states[8]);

		return output();
	}

	bool output(){
		return states[9];
	}
};