# 구현 과장 상세설명

# 1단계

### 기능 구현 목록

- 주어진 문자열 데이터를 저장값 형태로 변환하기
- 변환한 데이터를 이차원 배열 형태로 저장하기
- 데이터에서 필요한 정보 얻기
    - 가로크기
    - 세로크기
    - 구멍의 수
    - 공의 수
    - 플레이어 위치
- 출력할 지도 데이터 다시 기호 형태로 변환하기
- 지도와 지도정보 데이터 출력하기

# 2단계

- 정적 데이터를 저장하는 개체
    - 명령, 방향, 메세지 UserCommand,
- 동적 데이터를 저장하는 개체
    - 사용자의 위치 Position
    - 게임 관련 기능 SokobanGame , Play
    - 게임 실행(스테이지 선택), 스테이지 플레이
      데이터를 출력하는 기능 데이터 출력 기능 StageWriter,CmdStageWriter
    - 게임play 기능과 stage 기능을 구분
    - palygame 은 게임기능만, 게임을 하는 로직 (플레이어 이동)
    - sokobangame 은전박적인 stage 클리어 기능이나 게임을 실행
# 3단계


  - clear 여부 체크 -> 다음 스테이지
  - 이동 횟수 , 구멍이 들어가는 공수
  - stage 객체는 따 보관해두고 stage를 r로 초기화 해야하기때문에 init게임을 따로만든
  - 정적 데이터를 저장하는 개체
    공이 구멍에 들어간 상태, R(초기화) 명령어
  - 동적 데이터를 저장하는 개체
    플레이어 이동 횟수, 현재 구멍에 들어간 공 개수
  -  게임 관련 기능
    스테이지 클리어, 다음 스테이지, 전체 클리어 확인
  -  데이터를 읽는 기능
    파일 데이터 입력