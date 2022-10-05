package sokoban.Reader;

import sokoban.Data.Sign;
import sokoban.Data.Stage;
import sokoban.Data.StaticData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CmdStageReaderImpl implements StageReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public Stage readGameMap() throws IOException {
        String line = "";
        List<String> lines = new ArrayList<>();
        int stageNumber = 0;
        boolean isStageStart = false;

        while (true) {
            line = br.readLine();
            //스테이지 끝 부분
            if (lineValidation(line)) break;

            //스테이지 시작 하는 부분 stage를 체크  따로 구분해 Stage 생성자에 넘긴다.
            if (line.equals(StaticData.STAGE_START)) {
                stageNumber++; //총 몇시테이지 까지있는지 구한다
                isStageStart = true;
            }

            if (isStageStart) {
                lines.add(line);
            }
        }
        return new Stage(lines, stageNumber);
    }


    private boolean lineValidation(String line) {
        if (line == null || line.equals("")) {
            return true;
        }
        return false;
    }


}