g++ -shared Server/Compiled/Serv_comp.o Server/Compiled/Main.o -o Server/Compiled/Serv_comp.dll -lole32 -loleaut32 -luser32 -Wl,--kill-at

pause