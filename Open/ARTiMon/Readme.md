
	The ARTiMon specification is the file:
	
		hybrid_engine_cycles.spec
		
	The trace checked, in csv format, is the file:
	
		ws2_work_var_step.csv
	
	To run the benchmark
	
		double-click on ARTiMon.exe to execute (ARTiMon.exe has been compiled with Cygwin gcc)
		
		ARTiMon.exe generates two html verdict files. 
		
			Verdict_full.html is the most verbose verdict file. 
			
			Hazard a and b are detected at the end of the first cycle (time 3600.2) while
			
			hazard c is never detected (i.e. property c is not violated by the trace)
			
	The files of the original case study provided by Sherpa Engineering are in:

		Sherpa_Engineering_Ressources