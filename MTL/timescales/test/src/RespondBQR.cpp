#include "iostream"

#include "array"
#include "csv.h"

#include "MonitorRespondBQR.hpp"

int main(int argc, char **argv){

    int p, q, r, s;
    bool output;

    if (argc < 1) {
        std::cout <<  "ERROR: No input file" << std::endl;
        return 0;
    }

    auto monitor = MonitorRespondBQR<int>();

    io::CSVReader<4> reader(argv[1]);
    reader.read_header(io::ignore_extra_column, "p", "q", "r", "s");
    
    while(reader.read_row(p, q, r, s)){
        monitor.update(p, q, r, s);
        output = monitor.output();
        if(not output){
            std::cout << "Violation at time " << monitor.now << std::endl;
        }
    }

    return 0;
}
