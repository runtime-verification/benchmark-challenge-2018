J$.iids = {"8":[7,17,7,30],"9":[1,13,1,20],"10":[7,17,7,30],"16":[25,9,25,12],"17":[1,21,1,28],"18":[7,32,7,35],"25":[1,13,1,29],"33":[2,10,2,17],"34":[8,20,8,32],"41":[2,18,2,22],"42":[8,20,8,39],"49":[2,10,2,23],"50":[17,36,17,50],"57":[1,13,1,29],"58":[18,58,18,61],"65":[1,13,1,29],"73":[2,10,2,23],"74":[18,58,18,61],"81":[2,10,2,23],"89":[4,17,4,18],"97":[5,18,5,19],"105":[4,17,4,18],"113":[4,17,4,18],"121":[5,18,5,19],"129":[5,18,5,19],"137":[7,14,7,15],"145":[7,14,7,15],"153":[7,14,7,15],"161":[7,17,7,18],"169":[7,21,7,30],"185":[7,34,7,35],"201":[8,20,8,30],"209":[8,31,8,32],"217":[8,33,8,39],"225":[8,20,8,39],"233":[8,20,8,39],"241":[9,5,9,7],"249":[9,13,9,21],"257":[9,23,9,26],"265":[10,9,10,11],"273":[10,12,10,15],"281":[10,9,10,16],"289":[10,9,10,17],"297":[15,17,15,18],"305":[15,17,15,18],"313":[15,17,15,18],"321":[16,9,16,14],"329":[17,36,17,37],"337":[17,40,17,50],"345":[17,36,17,50],"353":[17,29,17,51],"361":[17,13,17,53],"369":[17,13,17,53],"377":[17,13,17,53],"385":[17,13,17,53],"393":[18,38,18,40],"401":[18,47,18,49],"409":[18,51,18,57],"425":[18,58,18,59],"449":[18,51,18,62],"457":[18,64,18,66],"465":[18,38,18,67],"467":[18,38,18,46],"473":[18,38,18,67],"481":[18,31,18,68],"489":[18,13,18,70],"497":[18,13,18,70],"505":[18,13,18,70],"513":[18,13,18,70],"521":[18,13,18,70],"529":[19,32,19,34],"537":[19,35,19,38],"545":[19,32,19,39],"553":[19,32,19,40],"561":[19,41,19,43],"569":[19,50,19,52],"577":[19,54,19,56],"585":[19,41,19,57],"587":[19,41,19,49],"593":[19,41,19,58],"601":[19,13,19,60],"609":[19,13,19,60],"617":[19,13,19,60],"625":[19,13,19,60],"633":[19,13,19,60],"641":[16,9,20,10],"643":[16,9,16,21],"649":[16,9,20,11],"657":[9,28,21,6],"665":[9,28,21,6],"673":[9,28,21,6],"681":[9,28,21,6],"689":[9,28,21,6],"697":[9,28,21,6],"705":[9,28,21,6],"713":[9,5,21,7],"715":[9,5,9,12],"721":[9,5,21,8],"729":[25,9,25,12],"737":[26,9,26,16],"745":[26,21,26,24],"753":[26,9,26,25],"755":[26,9,26,20],"761":[26,9,26,26],"769":[27,9,27,16],"777":[27,22,27,23],"785":[27,9,27,24],"787":[27,9,27,21],"793":[27,9,27,25],"801":[24,1,29,2],"809":[24,1,29,2],"817":[24,1,29,2],"825":[1,1,30,1],"833":[1,1,30,1],"841":[1,1,30,1],"849":[1,1,30,1],"857":[1,1,30,1],"865":[1,1,30,1],"873":[1,1,30,1],"881":[24,1,29,2],"889":[1,1,30,1],"897":[17,13,17,53],"905":[17,13,17,53],"913":[18,13,18,70],"921":[18,13,18,70],"929":[19,13,19,60],"937":[19,13,19,60],"945":[9,28,21,6],"953":[9,28,21,6],"961":[7,1,22,2],"969":[7,1,22,2],"977":[25,5,28,6],"985":[24,1,29,2],"993":[24,1,29,2],"1001":[1,1,30,1],"1009":[1,1,30,1],"nBranches":4,"originalCodeFileName":"/home/luca/vcs/git/benchmark-challenge-2018/Open/Node.js-RV/fs/many/prog/correct.js","instrumentedCodeFileName":"/home/luca/vcs/git/benchmark-challenge-2018/Open/Node.js-RV/fs/many/prog/correct_jalangi_.js","code":"var async = require('async'),\n    fs = require('fs');\n\nvar MAX_FILES = 1,\n    MAX_WRITES = 1;\n\nfor (var i = 0; i < MAX_FILES; ++i) {\n    var filename = '/tmp/out'+i+'.txt';\n    fs.open(filename, 'w', function opencb(err, fd) {\n        cb(err);\n        \n        //for (var i = 0; i < MAX_WRITES; ++i)\n        //    fs.write(fd, String(i), cb);\n        \n        var j = 0;\n        async.whilst(\n            function f1() { return j < MAX_WRITES; },\n            function f2(cb) { return fs.write(fd, String(j++), cb); },\n            function f3(err) { cb(err); fs.close(fd, cb); }\n        );\n    });\n}\n\nfunction cb(err) {\n    if (err) {\n        console.log(err);\n        process.exit(1);\n    }\n}\n"};
jalangiLabel5:
    while (true) {
        try {
            J$.Se(825, '/home/luca/vcs/git/benchmark-challenge-2018/Open/Node.js-RV/fs/many/prog/correct_jalangi_.js', '/home/luca/vcs/git/benchmark-challenge-2018/Open/Node.js-RV/fs/many/prog/correct.js');
            function cb(err) {
                jalangiLabel4:
                    while (true) {
                        try {
                            J$.Fe(801, arguments.callee, this, arguments);
                            arguments = J$.N(809, 'arguments', arguments, 4);
                            err = J$.N(817, 'err', err, 4);
                            if (J$.X1(977, J$.C(16, J$.R(729, 'err', err, 0)))) {
                                J$.X1(761, J$.M(753, J$.R(737, 'console', console, 2), 'log', 0)(J$.R(745, 'err', err, 0)));
                                J$.X1(793, J$.M(785, J$.R(769, 'process', process, 2), 'exit', 0)(J$.T(777, 1, 22, false)));
                            }
                        } catch (J$e) {
                            J$.Ex(985, J$e);
                        } finally {
                            if (J$.Fr(993))
                                continue jalangiLabel4;
                            else
                                return J$.Ra();
                        }
                    }
            }
            J$.N(833, 'async', async, 0);
            J$.N(841, 'fs', fs, 0);
            J$.N(849, 'MAX_FILES', MAX_FILES, 0);
            J$.N(857, 'MAX_WRITES', MAX_WRITES, 0);
            J$.N(865, 'i', i, 0);
            J$.N(873, 'filename', filename, 0);
            cb = J$.N(889, 'cb', J$.T(881, cb, 12, false, 801), 0);
            var async = J$.X1(65, J$.W(57, 'async', J$.F(25, J$.R(9, 'require', require, 2), 0)(J$.T(17, 'async', 21, false)), async, 3)), fs = J$.X1(81, J$.W(73, 'fs', J$.F(49, J$.R(33, 'require', require, 2), 0)(J$.T(41, 'fs', 21, false)), fs, 3));
            var MAX_FILES = J$.X1(113, J$.W(105, 'MAX_FILES', J$.T(89, 1, 22, false), MAX_FILES, 3)), MAX_WRITES = J$.X1(129, J$.W(121, 'MAX_WRITES', J$.T(97, 1, 22, false), MAX_WRITES, 3));
            for (var i = J$.X1(153, J$.W(145, 'i', J$.T(137, 0, 22, false), i, 3)); J$.X1(961, J$.C(8, J$.B(10, '<', J$.R(161, 'i', i, 1), J$.R(169, 'MAX_FILES', MAX_FILES, 1), 0))); J$.X1(969, i = J$.W(193, 'i', J$.B(26, '+', J$.U(18, '+', J$.R(185, 'i', i, 1)), J$.T(177, 1, 22, false), 0), i, 2))) {
                var filename = J$.X1(233, J$.W(225, 'filename', J$.B(42, '+', J$.B(34, '+', J$.T(201, '/tmp/out', 21, false), J$.R(209, 'i', i, 1), 0), J$.T(217, '.txt', 21, false), 0), filename, 3));
                J$.X1(721, J$.M(713, J$.R(241, 'fs', fs, 1), 'open', 0)(J$.R(249, 'filename', filename, 1), J$.T(257, 'w', 21, false), J$.T(705, function opencb(err, fd) {
                    jalangiLabel3:
                        while (true) {
                            try {
                                J$.Fe(657, arguments.callee, this, arguments);
                                arguments = J$.N(665, 'arguments', arguments, 4);
                                opencb = J$.N(673, 'opencb', opencb, 0);
                                err = J$.N(681, 'err', err, 4);
                                fd = J$.N(689, 'fd', fd, 4);
                                J$.N(697, 'j', j, 0);
                                J$.X1(289, J$.F(281, J$.R(265, 'cb', cb, 1), 0)(J$.R(273, 'err', err, 0)));
                                var j = J$.X1(313, J$.W(305, 'j', J$.T(297, 0, 22, false), j, 1));
                                J$.X1(649, J$.M(641, J$.R(321, 'async', async, 1), 'whilst', 0)(J$.T(385, function f1() {
                                    jalangiLabel0:
                                        while (true) {
                                            try {
                                                J$.Fe(361, arguments.callee, this, arguments);
                                                arguments = J$.N(369, 'arguments', arguments, 4);
                                                f1 = J$.N(377, 'f1', f1, 0);
                                                return J$.X1(353, J$.Rt(345, J$.B(50, '<', J$.R(329, 'j', j, 0), J$.R(337, 'MAX_WRITES', MAX_WRITES, 1), 0)));
                                            } catch (J$e) {
                                                J$.Ex(897, J$e);
                                            } finally {
                                                if (J$.Fr(905))
                                                    continue jalangiLabel0;
                                                else
                                                    return J$.Ra();
                                            }
                                        }
                                }, 12, false, 361), J$.T(521, function f2(cb) {
                                    jalangiLabel1:
                                        while (true) {
                                            try {
                                                J$.Fe(489, arguments.callee, this, arguments);
                                                arguments = J$.N(497, 'arguments', arguments, 4);
                                                f2 = J$.N(505, 'f2', f2, 0);
                                                cb = J$.N(513, 'cb', cb, 4);
                                                return J$.X1(481, J$.Rt(473, J$.M(465, J$.R(393, 'fs', fs, 1), 'write', 0)(J$.R(401, 'fd', fd, 0), J$.F(449, J$.R(409, 'String', String, 2), 0)(J$.B(74, '-', j = J$.W(433, 'j', J$.B(66, '+', J$.U(58, '+', J$.R(425, 'j', j, 0)), J$.T(417, 1, 22, false), 0), j, 0), J$.T(441, 1, 22, false), 0)), J$.R(457, 'cb', cb, 0))));
                                            } catch (J$e) {
                                                J$.Ex(913, J$e);
                                            } finally {
                                                if (J$.Fr(921))
                                                    continue jalangiLabel1;
                                                else
                                                    return J$.Ra();
                                            }
                                        }
                                }, 12, false, 489), J$.T(633, function f3(err) {
                                    jalangiLabel2:
                                        while (true) {
                                            try {
                                                J$.Fe(601, arguments.callee, this, arguments);
                                                arguments = J$.N(609, 'arguments', arguments, 4);
                                                f3 = J$.N(617, 'f3', f3, 0);
                                                err = J$.N(625, 'err', err, 4);
                                                J$.X1(553, J$.F(545, J$.R(529, 'cb', cb, 1), 0)(J$.R(537, 'err', err, 0)));
                                                J$.X1(593, J$.M(585, J$.R(561, 'fs', fs, 1), 'close', 0)(J$.R(569, 'fd', fd, 0), J$.R(577, 'cb', cb, 1)));
                                            } catch (J$e) {
                                                J$.Ex(929, J$e);
                                            } finally {
                                                if (J$.Fr(937))
                                                    continue jalangiLabel2;
                                                else
                                                    return J$.Ra();
                                            }
                                        }
                                }, 12, false, 601)));
                            } catch (J$e) {
                                J$.Ex(945, J$e);
                            } finally {
                                if (J$.Fr(953))
                                    continue jalangiLabel3;
                                else
                                    return J$.Ra();
                            }
                        }
                }, 12, false, 657)));
            }
        } catch (J$e) {
            J$.Ex(1001, J$e);
        } finally {
            if (J$.Sr(1009)) {
                J$.L();
                continue jalangiLabel5;
            } else {
                J$.L();
                break jalangiLabel5;
            }
        }
    }
// JALANGI DO NOT INSTRUMENT
