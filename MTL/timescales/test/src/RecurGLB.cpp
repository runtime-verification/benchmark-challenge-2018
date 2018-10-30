#include "iostream"

#include "array"
#include "csv.h"

#include "MonitorRecurGLB.hpp"

int main(int argc, char **argv){

    int p;
    bool output;

    if (argc < 1) {
        std::cout <<  "ERROR: No input file" << std::endl;
        return 0;
    }

    auto monitor = MonitorRecurGLB<int>();

    io::CSVReader<1> reader(argv[1]);
    reader.read_header(io::ignore_extra_column, "p");
    
    while(reader.read_row(p)){
        monitor.update(p);
        output = monitor.output();
        if(not output){
            std::cout << "Violation at time " << monitor.now << std::endl;
        }
    }

    return 0;
}
