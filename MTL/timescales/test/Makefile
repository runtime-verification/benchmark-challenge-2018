all: test

test: test_absent_aq test_absent_br test_absent_bqr test_always_aq test_always_br test_always_bqr test_recur_glb test_recur_bqr test_respond_glb test_respond_bqr test_challenge_pandq test_challenge_delay

test_absent_aq: 
	mkdir -p include
	reelay ../smallsuite/AbsentAQ.yaml --output-dir ./include --lang tl --with-headers 
	g++ -std=c++14 src/AbsentAQ.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/AbsentAQ.csv

test_absent_br: 
	mkdir -p include
	reelay ../smallsuite/AbsentBR.yaml --output-dir ./include --lang tl --with-headers
	g++ -std=c++14 src/AbsentBR.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/AbsentBR.csv

test_absent_bqr: 
	mkdir -p include
	reelay ../smallsuite/AbsentBQR.yaml --output-dir ./include --lang tl --with-headers
	g++ -std=c++14 src/AbsentBQR.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/AbsentBQR.csv

test_always_aq: 
	mkdir -p include
	reelay ../smallsuite/AlwaysAQ.yaml --output-dir ./include --lang tl --with-headers 
	g++ -std=c++14 src/AlwaysAQ.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/AlwaysAQ.csv

test_always_br: 
	mkdir -p include
	reelay ../smallsuite/AlwaysBR.yaml --output-dir ./include --lang tl --with-headers
	g++ -std=c++14 src/AlwaysBR.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/AlwaysBR.csv

test_always_bqr: 
	mkdir -p include
	reelay ../smallsuite/AlwaysBQR.yaml --output-dir ./include --lang tl --with-headers
	g++ -std=c++14 src/AlwaysBQR.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/AlwaysBQR.csv

test_recur_glb: 
	mkdir -p include
	reelay ../smallsuite/RecurGLB.yaml --output-dir ./include --lang tl --with-headers
	g++ -std=c++14 src/RecurGLB.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/RecurGLB.csv

test_recur_bqr: 
	mkdir -p include
	reelay ../smallsuite/RecurBQR.yaml --output-dir ./include --lang tl --with-headers
	g++ -std=c++14 src/RecurBQR.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/RecurBQR.csv

test_respond_glb: 
	mkdir -p include
	reelay ../smallsuite/RespondGLB.yaml --output-dir ./include --lang tl --with-headers
	g++ -std=c++14 src/RespondGLB.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/RespondGLB.csv

test_respond_bqr:
	mkdir -p include
	reelay ../smallsuite/RespondBQR.yaml --output-dir ./include --lang tl --with-headers
	g++ -std=c++14 src/RespondBQR.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/RespondBQR.csv

test_challenge_pandq:
	mkdir -p include 
	reelay ../smallsuite/ChallengePandq.yaml --output-dir ./include --lang tl --with-headers
	g++ -std=c++14 src/ChallengePandq.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/ChallengePandq.csv

test_challenge_delay:
	mkdir -p include 
	reelay ../smallsuite/ChallengeDelay.yaml --output-dir ./include --lang tl --with-headers
	g++ -std=c++14 src/ChallengeDelay.cpp -o monitor -I./include -pthread -O2
	./monitor ../smallsuite/ChallengeDelay.csv

clean:
	rm -rf include monitor