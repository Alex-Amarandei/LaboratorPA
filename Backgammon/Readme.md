# FX Backgammon

_Realizatori: Alex Amarandei, Ioana Pelin_

## Tehnologii utilizate:
1. Comunicare TCP/IP prin Socket-uri
2. JavaFX - alaturi de SceneBuilder
3. Multithreading si Executor

## Arhitecturi utilizate:
1. JavaFX GUI cu single thread specific
2. Client-Server
3. Master-Slave prin Multithreading
4. MVC (Model View Controller)

## De remarcat:
1. Interfata meniu responsive, cu binding-uri puse atat la nivelul dimensiunilor elementelor, cat si cele ale fonturilor
2. Stilizari folosind CSS
3. GameRoom-uri monitorizate si lansate in executie de catre un thread GameRoomSupervisor
4. Loading Screen pentru asteptarea repartizarii
5. Legatura stransa intre fisierele .fxml si controllere prin injectare 
6. Folosirea security.SecureRandom in detrimentul util.Random la alegerea zarurilor pentru permiterea aparitiei dublurilor
