all: small

small:
	mkdir -p smallsuite

	./reelay_benchgen absence_after_q --ubound 10 --duration 1000 --name AbsentAQ --output-dir smallsuite
	./reelay_benchgen absence_before_r --ubound 10 --duration 1000 --name AbsentBR --output-dir smallsuite
	./reelay_benchgen absence_between_q_and_r --lbound 3 --ubound 10 --duration 1000 --name AbsentBQR --output-dir smallsuite

	./reelay_benchgen always_after_q --ubound 10 --duration 1000 --name AlwaysAQ --output-dir smallsuite
	./reelay_benchgen always_before_r --ubound 10 --duration 1000 --name AlwaysBR --output-dir smallsuite
	./reelay_benchgen always_between_q_and_r --lbound 3 --ubound 10 --duration 1000 --name AlwaysBQR --output-dir smallsuite

	./reelay_benchgen recurrence_globally --lbound 3 --ubound 10 --duration 1000 --name RecurGLB --output-dir smallsuite
	./reelay_benchgen recurrence_between_q_and_r --lbound 3 --ubound 10 --duration 1000 --name RecurBQR --output-dir smallsuite

	./reelay_benchgen response_globally --lbound 3 --ubound 10 --duration 1000 --name RespondGLB --output-dir smallsuite
	./reelay_benchgen response_between_q_and_r --lbound 3 --ubound 10 --duration 1000 --name RespondBQR --output-dir smallsuite

	./reelay_benchgen challenge_pandq --lbound 30 --ubound 60 --duration 1000 --name ChallengePandq --output-dir smallsuite
	./reelay_benchgen challenge_delay --lbound 60 --ubound 60 --duration 1000 --name ChallengeDelay --output-dir smallsuite

large:
	mkdir -p largesuite/AbsentAQ	
	./reelay_benchgen absence_after_q --ubound 10 --duration 1000000 --name AbsentAQ10 --output-dir largesuite/AbsentAQ
	./reelay_benchgen absence_after_q --ubound 100 --duration 1000000 --name AbsentAQ100 --output-dir largesuite/AbsentAQ
	./reelay_benchgen absence_after_q --ubound 1000 --duration 1000000 --name AbsentAQ1000 --output-dir largesuite/AbsentAQ

	mkdir -p largesuite/AbsentBR
	./reelay_benchgen absence_before_r --ubound 10 --duration 1000000 --name AbsentBR10 --output-dir largesuite/AbsentBR
	./reelay_benchgen absence_before_r --ubound 100 --duration 1000000 --name AbsentBR100 --output-dir largesuite/AbsentBR
	./reelay_benchgen absence_before_r --ubound 1000 --duration 1000000 --name AbsentBR1000 --output-dir largesuite/AbsentBR

	mkdir -p largesuite/AbsentBQR
	./reelay_benchgen absence_between_q_and_r --lbound 3 --ubound 10 --duration 1000000 --name AbsentBQR10 --output-dir largesuite/AbsentBQR
	./reelay_benchgen absence_between_q_and_r --lbound 30 --ubound 100 --duration 1000000 --name AbsentBQR100 --output-dir largesuite/AbsentBQR
	./reelay_benchgen absence_between_q_and_r --lbound 300 --ubound 1000 --duration 1000000 --name AbsentBQR1000 --output-dir largesuite/AbsentBQR

	mkdir -p largesuite/AlwaysAQ
	./reelay_benchgen always_after_q --ubound 10 --duration 1000000 --name AlwaysAQ10 --output-dir largesuite/AlwaysAQ
	./reelay_benchgen always_after_q --ubound 100 --duration 1000000 --name AlwaysAQ100 --output-dir largesuite/AlwaysAQ
	./reelay_benchgen always_after_q --ubound 1000 --duration 1000000 --name AlwaysAQ1000 --output-dir largesuite/AlwaysAQ

	mkdir -p largesuite/AlwaysBR
	./reelay_benchgen always_before_r --ubound 10 --duration 1000000 --name AlwaysBR10 --output-dir largesuite/AlwaysBR
	./reelay_benchgen always_before_r --ubound 100 --duration 1000000 --name AlwaysBR100 --output-dir largesuite/AlwaysBR
	./reelay_benchgen always_before_r --ubound 1000 --duration 1000000 --name AlwaysBR100 --output-dir largesuite/AlwaysBR

	mkdir -p largesuite/AlwaysBQR
	./reelay_benchgen always_between_q_and_r --lbound 3 --ubound 10 --duration 1000000 --name AlwaysBQR10 --output-dir largesuite/AlwaysBQR
	./reelay_benchgen always_between_q_and_r --lbound 30 --ubound 100 --duration 1000000 --name AlwaysBQR100 --output-dir largesuite/AlwaysBQR
	./reelay_benchgen always_between_q_and_r --lbound 300 --ubound 1000 --duration 1000000 --name AlwaysBQR1000 --output-dir largesuite/AlwaysBQR

	mkdir -p largesuite/RecurGLB
	./reelay_benchgen recurrence_globally --lbound 3 --ubound 10 --duration 1000000 --name RecurGLB10 --output-dir largesuite/RecurGLB
	./reelay_benchgen recurrence_globally --lbound 30 --ubound 100 --duration 1000000 --name RecurGLB100 --output-dir largesuite/RecurGLB
	./reelay_benchgen recurrence_globally --lbound 300 --ubound 1000 --duration 1000000 --name RecurGLB1000 --output-dir largesuite/RecurGLB

	mkdir -p largesuite/RecurBQR
	./reelay_benchgen recurrence_between_q_and_r --lbound 3 --ubound 10 --duration 1000000 --name RecurBQR10 --output-dir largesuite/RecurBQR
	./reelay_benchgen recurrence_between_q_and_r --lbound 30 --ubound 100 --duration 1000000 --name RecurBQR100 --output-dir largesuite/RecurBQR
	./reelay_benchgen recurrence_between_q_and_r --lbound 300 --ubound 1000 --duration 1000000 --name RecurBQR1000 --output-dir largesuite/RecurBQR

	mkdir -p largesuite/RespondGLB
	./reelay_benchgen response_globally --lbound 3 --ubound 10 --duration 1000000 --name RespondGLB10 --output-dir largesuite/RespondGLB
	./reelay_benchgen response_globally --lbound 30 --ubound 100 --duration 1000000 --name RespondGLB100 --output-dir largesuite/RespondGLB
	./reelay_benchgen response_globally --lbound 300 --ubound 1000 --duration 1000000 --name RespondGLB1000 --output-dir largesuite/RespondGLB

	mkdir -p largesuite/RespondBQR
	./reelay_benchgen response_between_q_and_r --lbound 3 --ubound 10 --duration 1000000 --name RespondBQR10 --output-dir largesuite/RespondBQR
	./reelay_benchgen response_between_q_and_r --lbound 30 --ubound 100 --duration 1000000 --name RespondBQR100 --output-dir largesuite/RespondBQR
	./reelay_benchgen response_between_q_and_r --lbound 300 --ubound 1000 --duration 1000000 --name RespondBQR1000 --output-dir largesuite/RespondBQR

	mkdir -p largesuite/ChallengePandq
	./reelay_benchgen challenge_pandq --lbound 1 --ubound 10 --duration 1000000 --name ChallengePandq10 --output-dir largesuite/ChallengePandq
	./reelay_benchgen challenge_pandq --lbound 1 --ubound 100 --duration 1000000 --name ChallengePandq100 --output-dir largesuite/ChallengePandq
	./reelay_benchgen challenge_pandq --lbound 1 --ubound 1000 --duration 1000000 --name ChallengePandq1000 --output-dir largesuite/ChallengePandq

	mkdir -p largesuite/ChallengeDelay
	./reelay_benchgen challenge_delay --lbound 10 --ubound 10 --duration 1000000 --name ChallengeDelay --output-dir largesuite/ChallengeDelay
	./reelay_benchgen challenge_delay --lbound 100 --ubound 100 --duration 1000000 --name ChallengeDelay --output-dir largesuite/ChallengeDelay
	./reelay_benchgen challenge_delay --lbound 1000 --ubound 1000 --duration 1000000 --name ChallengeDelay --output-dir largesuite/ChallengeDelay

clean:
	rm -rf smallsuite largesuite