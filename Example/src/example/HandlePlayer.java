package example;

import java.io.IOException;

public class HandlePlayer {
    Player player;

    public HandlePlayer(Player player) {
        this.player = player;
    }

    public void handle() {

        Thread handlePlayer = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Entering handlePlayer1 thread");
                playerUpdate();
            }
        });
        handlePlayer.start();

//        Thread handlePlayer2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Entering handlePlayer2 thread");
//                playerUpdate();
//            }
//        });
//        handlePlayer2.start();

//        Thread broadcast = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Entering broadcast thread");
//                broadcastUpdate();
//            }
//        });
//        broadcast.start();

//        Thread activity = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                activityUpdate();
//            }
//        });
//        activity.start();


    }

    public void playerUpdate () {
        while (player.getIsActive()) {
            try {

                for (int i = 0; i < Multiplayer.activeShadow.size(); i++) {
                    if (!Multiplayer.activeShadow.elementAt(i).userInput.ready()) {
                        continue;
                    }
                    String msg = Multiplayer.activeShadow.elementAt(i).userInput.readLine();

                    String [] spliced = msg.split("#", 0);
                    if(player.getPlayerID() == 0) {
                        for (int j = 0; j < Multiplayer.activeShadow.size(); j++) {
                            if ((j + 1) == Integer.parseInt(spliced[0])) {
                                continue;
                            }
                            Multiplayer.activeShadow.elementAt(j).userOutput.println(msg);
                        }
                    }
                    //format ID#MOVE#playerPosX#PlayerPosY
                    //format ID#DEGREE#degree
                    //format ID#SHOOT#isShooting
                    //format ID#LIFE#isAlive

                    if (Integer.parseInt(spliced[0]) == LobbyController.thisPlayerID) {
                        continue;
                    }

                    if (spliced[1].equalsIgnoreCase("MOVE")) {
                        LobbyController.activePlayer.elementAt(Integer.parseInt(spliced[0])).setAbsolutePosX(Double.parseDouble(spliced[2]));
                        LobbyController.activePlayer.elementAt(Integer.parseInt(spliced[0])).setAbsolutePosY(Double.parseDouble(spliced[3]));
                    }
                    else if (spliced[1].equalsIgnoreCase("DEGREE")) {
                        LobbyController.activePlayer.elementAt(Integer.parseInt(spliced[0])).setDegree(Double.parseDouble(spliced[2]));
                    }
                    else if (spliced[1].equalsIgnoreCase("SHOOT")) {
                        System.out.println(msg);
                        LobbyController.activePlayer.elementAt(Integer.parseInt(spliced[0])).setIsShooting(Boolean.parseBoolean(spliced[2]));
                    }
                    else if (spliced[1].equalsIgnoreCase("LIFE")) {
                        LobbyController.activePlayer.elementAt(Integer.parseInt(spliced[0])).setIsAlive(Boolean.parseBoolean(spliced[2]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMovement () {
        for (int i = 0; i < Multiplayer.activeShadow.size(); i++) {
            Multiplayer.activeShadow.elementAt(i).userOutput.println(player.getPlayerID() + "#" + "MOVE" + "#" + player.getAbsolutePosX() + "#" + player.getAbsolutePosY());
        }
    }

    public void broadcastDegree () {
        for (int i = 0; i < Multiplayer.activeShadow.size(); i++) {
            Multiplayer.activeShadow.elementAt(i).userOutput.println(player.getPlayerID() + "#" + "DEGREE" + "#" + player.getDegree());
        }
    }

    public void broadcastLife () {
        for (int i = 0; i < Multiplayer.activeShadow.size(); i++) {
            Multiplayer.activeShadow.elementAt(i).userOutput.println(player.getPlayerID() + "#" + "LIFE" + "#" + player.getIsAlive());
        }
    }

    public void broadcastShoot (String msg) {
        for (int i = 0; i < Multiplayer.activeShadow.size(); i++) {
            Multiplayer.activeShadow.elementAt(i).userOutput.println(msg);
        }
    }

    public void activityUpdate () {
        while (player.getIsActive()) {
            try {
                String msg = player.getShadow().userInput.readLine();
                String[] spliced = msg.split("#", 0);

                if(spliced[0].equalsIgnoreCase(String.valueOf(player.getPlayerID())) && spliced[1].equalsIgnoreCase("ACTIVITY")) {
                    if (!Boolean.parseBoolean(spliced[2])) {
                        //house keeping code
                        player.setIsAlive(false);
                        player.setIsActive(false);
                        Multiplayer.activeShadow.remove(player.getShadow());
                        LobbyController.activePlayer.remove(player);
                        player.getShadow().userInput.close();
                        player.getShadow().userOutput.close();
                        player.getShadow().getS().close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
