#include "array"
#include "discrete_time.hpp"

template<typename T = int>
struct MonitorRespondGLB {

	std::array<bool,4> states = std::array<bool,4>{0,0,0,0};
	std::array<bool,4> states_pre = std::array<bool,4>{0,0,0,0};

	std::array<interval_set<T>,2> tstates = std::array<interval_set<T>,2>{interval_set<T>(),interval_set<T>()};
	std::array<interval_set<T>,2> tstates_pre = std::array<interval_set<T>,2>{interval_set<T>(),interval_set<T>()};

	T now = 0;

	bool update(bool p, bool s){

		now = now + 1;
		states_pre = states;
		tstates_pre = tstates;

		tstates[0] = update_timed_since<T>(tstates[0], now, true, p, 3, 10);
		states[0] = (output_timed_since<T>(tstates[0], now) or not(s));
		states[1] = not(s);
		tstates[1] = update_timed_since_unbounded<T>(tstates[1], now, states[1], p, 10);
		states[2] = not(output_timed_since<T>(tstates[1], now));
		states[3] = (states[0] and states[2]);

		return output();
	}

	bool output(){
		return states[3];
	}
};