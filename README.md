# 블랙잭 간단한 룰

### 핵심 룰 : 각카드 숫자의합이 21에 가까운 사람이 승리 (21초과시 패배)

- 각유저 vs 딜러 대결

- 배팅은 승리시 2배, 블랙잭으로 승리시 1.5배

- 유저카드 숫자의 합이 21이 가까워 지도록 hit / stand 를 선택

  - hit : 추가 뽑기
  - stand : 현상태 유지후 턴 종료


## 미션 진행

- Step1 (유저와 딜러 1:1 콘솔게임 구현)

  > ~~Player, CardDeck 객체생성~~
  >
  > ~~Card enum 구현~~
  >
  > ~~CardDeck, Player 연관관계 구현~~

  - 구현예정

  > User가 hti / stand를 선택해 Card를 받을 수 있도록 구현
  >
  > Burst (21초과)일시 패배
  >
  > 게임 구성원들의 칩 관리 



- Step2 (유저와 딜러 M:1 콘솔게임 구현, 세부룰)

  > 구현예정
  >
  > 리팩토링
  >
  > 여러명의 유저 동시 진행
  >
  > 세부룰 구현 
  >
  > - 스플릿
  > - 더블

- Step3

  > 구현예정 
  >
  > 리팩토링

- Step4

  > 웹으로 구현 예정
