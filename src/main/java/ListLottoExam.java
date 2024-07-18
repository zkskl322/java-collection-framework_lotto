import java.util.*;

public class ListLottoExam {
    public static void main(String[] args) {
        // 45개 공을 담을 리스트
        List<Integer> balls = new ArrayList<>();
        // 로또를 저장할 리스트
        List<Integer> lotto = new ArrayList<>();
        // 보너스번호 저장
        int bonusNumber = 0;

        // 45개 공을 생성
        for(int i = 0; i < 45; i++) {
            balls.add(i + 1);
        }

        // 컬렉션 프레임워크를 도와주는 클래스 Arrays와 동일
        Collections.shuffle(balls);

        // 공을 추첨
        int count = 0;
        while (count < 7) {
            // 리스트를 섞는다.
            Collections.shuffle(balls);

            if(count < 6) {
                lotto.add(balls.get(0));
                // 중복제거를 위해서 공을 지운다.
                balls.remove(0);
            }else {
                bonusNumber = balls.get(0);
            }
            count++; // 꺼낸 공개수 증가
        }

        System.out.println("로또 : " + lotto +", 보너스번호 : " + bonusNumber);

        // 사용자 로또
        count = 0;
        Scanner scan = new Scanner(System.in);
        List<Integer> myLotto = new ArrayList<>();
        // 사용자 로또 선택
        while (count < 6) {
            try {
                System.out.println((count + 1) + "번째 로또 : ");
                int ball = scan.nextInt();

                // 공의 범위 체크
                if (ball < 1 || ball > 45) {
                    // 임의의 예외 던지기
                    throw new InputMismatchException("키입력은 1에서 45사이여야 합니다.");
                }

                // 해당 값이 리스트에 존재하는지 확인
                if (myLotto.contains(ball)) {
                    System.out.println(ball + " 번호는 이미 선택하셨습니다.");
                    continue;
                }

                myLotto.add(ball);
                count++;
            }catch (InputMismatchException e) {
                // 키입력 버퍼에 찌꺼지 제거 - flush -> auto flush
                scan.nextLine();
                System.out.println(e.getMessage() == null ? "키입력이 잘못되었습니다." : e.getMessage());
            }
        }

        // 사용다한 스캐너 종료
        scan.close();

        //  비교를 위한 객체
        List<Integer> matchNumbers = new ArrayList<>();
        int matchCount = 0;
        boolean isBonus = false;
        for(int myBall : myLotto) {
            // 내가 선택한 번호가 로또 번호중에 있다면
            if(lotto.contains(myBall)) {
                // 저장
                matchNumbers.add(myBall);
            }

            // 보너스번호를 못찾았을때만 비교
            if(!isBonus) {
                // 내 공번호와 보너스번호가 같다면
                if(myBall == bonusNumber) {
                    isBonus = true;
                }
            }
        }

        matchCount = matchNumbers.size();

        System.out.print("맞은번호 : " + matchNumbers);
        System.out.println(matchCount == 5 && isBonus ? ", 보너스번호 : " + bonusNumber : "");

        if(matchCount == 6) {
            System.out.println("인생역전!! 대박!! 로또 1등입니다.!!");
        }else if(matchCount == 5 && isBonus) {
            System.out.println("대박!! 로또 2등입니다.!!");
        }else if(matchCount == 5) {
            System.out.println("우와!! 로또 3등입니다.!!");
        }else if(matchCount == 4) {
            System.out.println("4등입니다.!!");
        }else if(matchCount == 3) {
            System.out.println("5등입니다.!!");
        }else {
            System.out.println("아쉽네요. 낙첨입니다. 다음에는 당신이 1등!!");
        }
    }
}