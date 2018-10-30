#include "iostream"

#include "array"
#include "csv.h"

#include "MonitorAbsentBQR.hpp"

int main(int argc, char **argv){

    int p, q, r;
    bool output;

    if (argc < 1) {
        std::cout <<  "ERROR: No input file" << std::endl;
        return 0;
    }

    auto monitor = MonitorAbsentBQR<int>();

    io::CSVReader<3> reader(argv[1]);
    reader.read_header(io::ignore_extra_column, "p", "q", "r");
    
    while(reader.read_row(p, q, r)){
        monitor.update(p, q, r);
        output = monitor.output();
        if(not output){
            std::cout << "Violation at time " << monitor.now << std::endl;
        }
    }

    return 0;
}
