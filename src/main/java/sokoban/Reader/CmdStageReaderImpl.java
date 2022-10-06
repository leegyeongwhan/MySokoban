package sokoban.Reader;

import sokoban.Data.Stage;
import sokoban.Data.StageUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CmdStageReaderImpl implements StageReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public Stage readStageMap() throws IOException {
        String line = "";
        List<String> lines = new ArrayList<>();
        int stageNumber = 0;
        boolean isStageStart = false;

        while (true) {
            line = br.readLine();
            //입력의 끝
            if (lineValidation(line)) {
                break;
            }

            // 스테이지 끝부분
            if (StageUtil.isEndStage(line)) {
                break;
            }
            //스테이지 시작 하는 부분 stage를 체크  따로 구분해 Stage 생성자에 넘긴다.
            if (StageUtil.isStartStage(line)) {
                stageNumber++; //총 몇시테이지 까지있는지 구한다
                isStageStart = true;
                continue;
            }

            //읽은 데이터를 저장
            if (isStageStart) {
                lines.add(line);
            }
        }
        return new Stage(lines, stageNumber);
    }

    @Override
    public List<Stage> readAllGameMap() throws IOException {
        List<Stage> gameMapList = new ArrayList<>();

        while (!isClosed()) {
            gameMapList.add(readStageMap());
        }

        return gameMapList;
    }

    private boolean isClosed() {
        return br == null;
    }


    private boolean lineValidation(String line) {
        if (line == null || line.equals("")) {
            return true;
        }
        return false;
    }


}