package sokoban.Data;


import java.util.Arrays;

/***
 *
 * 시작 stage
 * 끝 ======
 기호	의미	저장값
 #	벽(Wall)	0
 O	구멍(Hall)	1
 o	공(Ball)	2
 P	플레이어(Player)	3
 =	스테이지 구분	4
 */
public enum StaticData {
    STAGE_START("Stage"), STAGE_END("=");

    private String keyword;

    StaticData(String keyword) {
        this.keyword = keyword;
    }

    private String getKeyword() {
        return keyword;
    }
}
