package TicTacToe.network;

import static TicTacToe.GameControler.GameController.Tile;

public class UpdateBoardMessage extends MultiplayerMessage{

    public Tile[][] board = new Tile[3][3];

    public UpdateBoardMessage() {
        super(UPDATE_BOARD_MESSAGE);
    }

    public UpdateBoardMessage(Tile[][] board) {
        super(UPDATE_BOARD_MESSAGE);
        this.board = board;
    }

    @Override
    public String asString() {
        String data = UPDATE_BOARD_MESSAGE;

        for(Tile[] row:board){
            for (Tile tile:row){
                data += tile.name()+" ";
            }
        }

        return data.trim();
    }

    @Override
    public void fromString(String s) {
        String[] data = s.substring(UPDATE_BOARD_MESSAGE.length()).split("\\s");
        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Tile.valueOf(data[x++]);
            }
        }
    }
}
