# WEEK1
CS496 PJ1

- Tap 3개로 이루어진 Android 어플리케이션입니다. 

## 탭 구현
- BottomNavigationView 를 이용하여 탭 간의 이동을 할 수 있도록 했습니다. 
- 또한, viewpager를 이용하여 탭을 터치했을 때 뿐만 아니라, 슬라이드를 통해서도 탭 사이를 이동할 수 있도록 했습니다.


## Tap1: 연락처 📱
- 첫번째 탭은 연락처를 보여주는 화면입니다. 
- 리사이클러 뷰를 이용하여 연락처 리스트를 관리할 수 있도록 했습니다.

![KakaoTalk_Photo_2022-07-05-21-04-30 001](https://user-images.githubusercontent.com/103231425/177323303-af93e073-fe6c-465f-8d2b-5a571bc637d6.png)



## Tap2 : 갤러리 🏞
- 두번째 탭은 갤러리를 보여주는 화면입니다.
- GridView 와 리사이클러 뷰를 이용하여 갤러리를 구현했습니다.
- 그리드를 선택하면 새로운 image Activity를 불러오도록 했습니다. 
- image Acitity 내부는 뷰페이저와 photoView를 이용하여 내부 이미지 사이에서 슬라이드가 가능하도록 했고, zoom in/ zoom out 이 가능하도록 했습니다.

![KakaoTalk_Photo_2022-07-05-21-04-30 002](https://user-images.githubusercontent.com/103231425/177323356-c4424f39-ffb3-4bcf-92ca-e6ef1ba59885.png)



##  Tap3 : Habit Tracker 📝
- 세번째 탭은 달력과 to do를 이용하여 습관을 기록하고 체크할 수 있는 화면입니다.
- 첫 화면에서 체크하고자 하는 목록을 추가할 수 있습니다.
- 추가할 때 기록할 습관의 시작 날짜와 종료 날짜를 지정하여 저장할 수 있습니다.


- 습관 목록을 눌러 달력 화면을 불러 올 수 있습니다.
- 달력 화면에서는 원형 차트로 그날의 달성정도를 볼 수 있습니다. 

- 상단의 calendar 버튼을 누르면 습관을 기록할 날짜를 변경 할 수 있습니다.

- 우 상단의 edit 버튼을 누른 후 done 상태가 되면 습관 목록을 클릭하여 저장된 습관 목록을 수정할 수 있습니다. 

![KakaoTalk_Photo_2022-07-05-21-04-30 004](https://user-images.githubusercontent.com/103231425/177323476-12eff270-88fd-45b5-bbfb-6567a93ec045.png) ![KakaoTalk_Photo_2022-07-05-21-04-30 003](https://user-images.githubusercontent.com/103231425/177323370-8f41db3f-4df2-4335-9f51-79485298e86d.png)
