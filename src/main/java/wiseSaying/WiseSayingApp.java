package wiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//8단계
public class WiseSayingApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<WiseSaying> wiseSayingList = new ArrayList<>();

        int id = 1;

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명언) ");
            String input = scanner.nextLine();

            if (input.equals("종료")) {
                break;
            } else if (input.equals("등록")) {
                create(scanner, id, wiseSayingList);
                id++;

            } else if (input.equals("목록")) {
                getAll(wiseSayingList);

            } else if (input.startsWith("삭제?id=")) {
                delete(input, wiseSayingList);

            } else if (input.startsWith("수정?id=")) {
                update(input, wiseSayingList, scanner);

            } else {
                System.out.println("명령어를 다시 입력해주세요");
            }
        }
    }

    private static void create(Scanner scanner, int id, List<WiseSaying> wiseSayingList) {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        createWiseSaying(id, wiseSayingList, content, author);

        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    private static void createWiseSaying(int id, List<WiseSaying> wiseSayingList, String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayingList.add(wiseSaying);
    }

    private static void delete(String input, List<WiseSaying> wiseSayingList) {
        String[] parts = input.split("=");
        int deleteId = Integer.parseInt(parts[1]);
        boolean found = false;

        for (int i = 0; i < wiseSayingList.size(); i++) {
            if (wiseSayingList.get(i).id == deleteId) {
                wiseSayingList.remove(i);
                System.out.println(deleteId + "번 명언이 삭제되었습니다.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        }
    }

    private static void getAll(List<WiseSaying> wiseSayingList) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = wiseSayingList.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = wiseSayingList.get(i);
            System.out.println(wiseSaying.id + " / " + wiseSaying.author + " / " + wiseSaying.content);
        }
    }

    private static void update(String input, List<WiseSaying> wiseSayingList, Scanner scanner) {
        String[] parts = input.split("=");
        int insertId = Integer.parseInt(parts[1]);
        boolean found = false;

        for (WiseSaying wiseSaying : wiseSayingList) {
            if (wiseSaying.id == insertId) {
                System.out.println("명언(기존) : " + wiseSaying.content);
                System.out.print("명언 : ");
                wiseSaying.content = scanner.nextLine();

                System.out.println("작가(기존) : " + wiseSaying.author);
                System.out.print("작가 : ");
                wiseSaying.author = scanner.nextLine();

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(insertId + "번 명언은 존재하지 않습니다.");
        }
    }

}
