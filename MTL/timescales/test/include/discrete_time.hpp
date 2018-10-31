#include <boost/icl/interval.hpp>
#include <boost/icl/interval_set.hpp>

using namespace boost::icl;

template<typename T = int>
interval_set<T>& update_timed_since(interval_set<T>& state, T now, bool p1, bool p2, T lower, T upper){
   
    if(p1 and p2){
        state = state.add(interval<T>::closed(now+lower, now+upper));
        state = state - interval_set<T>(interval<T>::right_open(0, now));
    } else if(!p1 and p2){
        state = interval_set<T>(interval<T>::closed(now+lower, now+upper));
    } else if(p1 and !p2){
        // state = state - interval_set<T>(interval<T>::right_open(0, now));
    } else {
        state = interval_set<T>();
    }

    return state;
}

template<typename T = int>
interval_set<T>& update_timed_since_unbounded(interval_set<T>& state, T now, bool p1, bool p2, T lower){
   
    if(p1 and p2){
        state = state.add(interval<T>::closed(now+lower, std::numeric_limits<T>::max()));
        state = state - interval_set<T>(interval<T>::right_open(0, now));
    } else if(!p1 and p2){
        state = interval_set<T>(interval<T>::closed(now+lower, std::numeric_limits<T>::max()));
    } else if(p1 and !p2){
        // state = state - interval_set<T>(interval<T>::right_open(0, now));
    } else {
        state = interval_set<T>();
    }

    return state;
}

template<typename T = int>
bool output_timed_since(interval_set<T>& state, T now){
    return contains(state, now);
}


/*
 * Predicates over Real-Valued Signals
 */

template <class V, class T>
bool lt(V value, T threshold){
    return value < threshold;
}
template <class V, class T>
bool leq(V value, T threshold){
    return value <= threshold;
}
template <class V, class T>
bool gt(V value, T threshold){
    return value > threshold;
}
template <class V, class T>
bool geq(V value, T threshold){
    return value >= threshold;
}

