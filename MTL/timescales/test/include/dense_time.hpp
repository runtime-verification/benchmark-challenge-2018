#include <limits>
#include <vector>

#include <boost/icl/interval.hpp>
#include <boost/icl/interval_set.hpp>

using namespace boost::icl;

// constexpr static T infinity = (std::numeric_limits<T>::has_infinity) ? std::numeric_limits<T>::infinity() : std::numeric_limits<T>::max();


/*
 *  This is the sychronized case where propositions are constant over the current period.
 */
template<typename T = int>
interval_set<T> update_timed_since(
    interval_set<T>& state,
    const interval_set<T>& current,
    bool p1,
    bool p2,
    T a,
    T b
    ){

    if(is_empty(current)){
        return state;
    }

    if(p1 and p2){
        state.add(interval<T>::left_open(current.begin()->lower()+a, current.begin()->upper()+b));

        // Comment out the line below for lazy trimming
        // state = state & interval_set<T>(interval<T>::left_open(current.begin()->lower(), std::numeric_limits<T>::max()));

    } else if(!p1 and p2) {

        state = state & interval<T>::left_open(0, current.begin()->lower());
        state.add(interval<T>::left_open(current.begin()->upper()+a, current.begin()->upper()+b));

    } else if(p1 and !p2){

        // Comment out the line below for lazy trimming
        // state = state & interval_set<T>(interval<T>::left_open(current.begin()->lower(), std::numeric_limits<T>::max()));

    } else {
        state = state & interval<T>::left_open(0, current.begin()->lower());
    }
    
    return state;
}


/*
 *  Specialize the update_timed_since method for the case b = Inf (unbounded)
 */
template<typename T = int>
interval_set<T> update_timed_since_unbounded(
    interval_set<T>& state,
    const interval_set<T>& current,
    bool p1,
    bool p2,
    T a
    ){

    if(is_empty(current)){
        return state;
    }

    if(p1 and p2){
        state.add(interval<T>::left_open(current.begin()->lower()+a, std::numeric_limits<T>::max()));
        // Comment out the line below for lazy trimming
        // state = state & interval_set<T>(interval<T>::left_open(current.begin()->lower(), std::numeric_limits<T>::max()));
    } else if(!p1 and p2) {
        state = state & interval<T>::left_open(0, current.begin()->lower());
        state.add(interval<T>::left_open(current.begin()->upper()+a, std::numeric_limits<T>::max()));
    } else if(p1 and !p2){
        // Comment out the line below for lazy trimming
        // state = state & interval_set<T>(interval<T>::left_open(current.begin()->lower(), std::numeric_limits<T>::max()));
    } else {
        state = state & interval<T>::left_open(0, current.begin()->lower());
    }
    
    return state;
}

/*
 *  This is the most general case such that all arguments are given as chunks.
 */
template<typename T = int>
interval_set<T>  update_timed_since(
    interval_set<T>& state,
    const interval_set<T>& current,
    const interval_set<T>& p1set,
    const interval_set<T>& p2set,
    T a,
    T b
    ){

    /*
     *  The following code performs the sychronization between two arbitrary chunks and 
     *  calls the update_timed_since for each constant period sequentially. The synchronization 
     *  algorithm is a variant of the plane sweep algorithm from computational geometry.
     */

    T time = current.begin()->lower();

    bool p1 = false;
    bool p2 = false;

    std::vector<T> bounds1; //bounds1.reserve(p1set.size()*2+1);
    std::vector<T> bounds2; //bounds2.reserve(p2set.size()*2+1);

    for(const auto& intv : p1set){
        bounds1.push_back(intv.lower());
        bounds1.push_back(intv.upper());
    }
    bounds1.push_back(current.begin()->upper());

    for(const auto& intv : p2set){
        bounds2.push_back(intv.lower());
        bounds2.push_back(intv.upper());
    }
    bounds2.push_back(current.begin()->upper());

    auto it1 = bounds1.begin();
    auto it2 = bounds2.begin();

    state = state & interval_set<T>(interval<T>::left_open(current.begin()->lower(), std::numeric_limits<T>::max()));

    while(it1 != bounds1.end() and it2 != bounds2.end()){

        if(*it1 < *it2){
            state = update_timed_since(state, interval_set<T>(interval<T>::left_open(time, *it1)), p1, p2, a,b);
            p1 = not p1;
            time = *it1;
            it1++;

        } else if (*it1 > *it2) {
            state = update_timed_since(state, interval_set<T>(interval<T>::left_open(time, *it2)), p1, p2, a,b);
            p2 = not p2;
            time = *it2;
            it2++;

        } else { // *it1 == *it2
            state = update_timed_since(state, interval_set<T>(interval<T>::left_open(time, *it1)), p1, p2, a,b);
            p1 = not p1;
            p2 = not p2;
            time = *it1;
            it1++;
            it2++;
        }

    }

    return state;
}

/*
 *  Specialized the general update_timed_since method for the case b = Inf (unbounded)
 */
template<typename T = int>
interval_set<T> update_timed_since_unbounded(
    interval_set<T>& state,
    const interval_set<T>& current,
    const interval_set<T>& p1set,
    const interval_set<T>& p2set,
    T a
    ){

    /*
     *  The following code performs the sychronization between two arbitrary chunks and 
     *  calls the update_timed_since for each constant period sequentially. The synchronization 
     *  algorithm is a variant of the plane sweep algorithm from computational geometry.
     */

    T time = current.begin()->lower();

    bool p1 = false;
    bool p2 = false;

    std::vector<T> bounds1; //bounds1.reserve(p1set.size()*2+1);
    std::vector<T> bounds2; //bounds2.reserve(p2set.size()*2+1);

    // bounds1.push_back(current.begin()->lower());
    for(const auto& intv : p1set){
        bounds1.push_back(intv.lower());
        bounds1.push_back(intv.upper());
    }
    bounds1.push_back(current.begin()->upper());

    // bounds2.push_back(current.begin()->lower());
    for(const auto& intv : p2set){
        bounds2.push_back(intv.lower());
        bounds2.push_back(intv.upper());
    }
    bounds2.push_back(current.begin()->upper());

    auto it1 = bounds1.begin();
    auto it2 = bounds2.begin();

    state = state & interval_set<T>(interval<T>::left_open(current.begin()->lower(), std::numeric_limits<T>::max()));

    while(it1 != bounds1.end() and it2 != bounds2.end()){

        if(*it1 < *it2){
            // std::cout << time << ' '<< *it1 << '|' << p1 << p2 << std::endl;
            state = update_timed_since_unbounded(state, interval_set<T>(interval<T>::left_open(time, *it1)), p1, p2, a);
            p1 = not p1;
            time = *it1;
            it1++;

        } else if (*it1 > *it2) {
            // std::cout << time << ' '<< *it2 << '|' << p1 << p2 << std::endl;
            state = update_timed_since_unbounded(state, interval_set<T>(interval<T>::left_open(time, *it2)), p1, p2, a);
            p2 = not p2;
            time = *it2;
            it2++;

        } else { // *it1 == *it2
            // std::cout << time << ' '<< *it1 << '|' << p1 << p2 << std::endl;
            state = update_timed_since_unbounded(state, interval_set<T>(interval<T>::left_open(time, *it1)), p1, p2, a);
            p1 = not p1;
            p2 = not p2;
            time = *it1;
            it1++;
            it2++;
        }

    }

    return state;
}


/*
 *  Output via intersection
 */
template<typename T = int>
interval_set<T> output_timed_since(const interval_set<T>& state, const interval_set<T>& current){
    return state & current;
}



/*
 *  Propositions to Interval Sets
 */
template<typename T = int>
interval_set<T> prop(const interval_set<T>& current, bool p){
    if(p){
        return current;
    } else {
        return interval_set<T>();
    }
}

// template <class V, class T>
// bool lt(V value, T threshold){
//     return value < threshold;
// }
// template <class V, class T>
// bool leq(V value, T threshold){
//     return value <= threshold;
// }
// template <class V, class T>
// bool gt(V value, T threshold){
//     return value > threshold;
// }
// template <class V, class T>
// bool geq(V value, T threshold){
//     return value >= threshold;
// }


