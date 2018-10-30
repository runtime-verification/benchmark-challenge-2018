#include "iostream"

#include "array"
#include "csv.h"

#include "MonitorAbsentBR.hpp"

int main(int argc, char **argv){

    int p, r;
    bool output;

    if (argc < 1) {
        std::cout <<  "ERROR: No input file" << std::endl;
        return 0;
    }

    auto monitor = MonitorAbsentBR<int>();

    io::CSVReader<2> reader(argv[1]);
    reader.read_header(io::ignore_extra_column, "p", "r");
    
    while(reader.read_row(p, r)){
        monitor.update(p, r);
        output = monitor.output();
        if(not output){
            std::cout << "Violation at time " << monitor.now << std::endl;
        }
    }

    return 0;
}
