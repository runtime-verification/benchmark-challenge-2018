#include "array"
#include "discrete_time.hpp"

template<typename T = int>
struct MonitorRecurGLB {

	std::array<interval_set<T>,1> tstates = std::array<interval_set<T>,1>{interval_set<T>()};
	std::array<interval_set<T>,1> tstates_pre = std::array<interval_set<T>,1>{interval_set<T>()};

	T now = 0;

	bool update(bool p){

		now = now + 1;
		tstates_pre = tstates;

		tstates[0] = update_timed_since<T>(tstates[0], now, true, p, 0, 10);

		return output();
	}

	bool output(){
		return output_timed_since<T>(tstates[0], now);
	}
};