package KU_hotel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static KU_hotel.Main.date;

public class RoomInfo {
    private String checkIn, userName, checkOut, PhoneNum;
    int personNum, price, roomNum;
    private boolean isAccept;

    User user = new User();

    public RoomInfo() {
    }


    public RoomInfo(User user) {
        this.user = user;
    }

    public RoomInfo(int roomNum, String userName, String phoneNum, String checkIn, String checkOut, int personNum, int price, boolean isAccept) {
        this.checkIn = checkIn;
        this.userName = userName;
        this.checkOut = checkOut;
        this.PhoneNum = phoneNum;
        this.personNum = personNum;
        this.price = price;
        this.roomNum = roomNum;
        this.isAccept = isAccept;
    }

    ArrayList<RoomInfo> rooms = new ArrayList<RoomInfo>();
    final String filename = "src/KU_hotel/RoomInfo.csv";


    public String getcheckIn() {
        return checkIn;
    }

    public void setcheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getcheckOut() {
        return checkOut;
    }

    public void setcheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getpersonNum() {
        return personNum;
    }

    public void setpersonNum(int personNum) {
        this.personNum = personNum;
    }

    public int getprice() {
        return price;
    }

    public void setprice(int price) {
        this.price = price;
    }

    public int getroomNum() {
        return roomNum;
    }

    public void setroomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }

    public boolean getisAccept() {
        return isAccept;
    }

    public void setisAccept(boolean isAccept) {
        this.isAccept = isAccept;
    }


    public void reservation_Menu() {
        Scanner sc = new Scanner(System.in);
        fromCsv();
        while (true) {
            System.out.println("<예약 메뉴>");
            System.out.println("---------------");
            System.out.println("1) 예약 신청");
            System.out.println("2) 예약 확인");
            System.out.println("3) 로그아웃");
            System.out.println("---------------");
            try {
                System.out.println("메뉴 번호를 입력하세요.");
                System.out.print(">>");
                String choice = sc.nextLine().trim();
                switch (choice) {
                    case "1":
                        apply_Reservation();
                        break;
                    case "2":
                        check_Reservation();
                        break;
                    case "3":
                        System.out.println("메뉴로 돌아갑니다.");
                        return;
                    default:
                        System.out.println("1~3 사이 숫자를 입력하세요");
                }
            } catch (NumberFormatException E) {
                System.out.println("올바른 형식으로 입력하세요!");
            }
        }
    }


    public void apply_Reservation() {
        String checkIn, checkOut, choose;
        int myroomNum = 0;
        int personNum;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("체크인 날짜를 입력해주세요.(YYYYMMDD)");
            System.out.print(">>");
            checkIn = sc.nextLine();
            checkIn = checkIn.trim();
            if (checkIn.equals('q')) {
                return;
            } else if (checkIn.length() != 8) {
                System.out.println("날짜는 YYYYMMDD 형식으로 입력해야합니다.");
                continue;
            } else if (!checkIn.matches("\\d+")) { // date가 숫자가 아닐 시
                System.out.println("날짜는 YYYYMMDD 형식의 숫자로 입력해야합니다.");
                continue;
            }

            int num1 = Integer.parseInt(Main.date);
            int num2 = Integer.parseInt(checkIn);

            if (num1 > num2) {
                System.out.println("이전 날짜는 예약할 수 없습니다.");
                System.out.println("오늘은 " + Main.date + "입니다.");
                continue;
            } else {
                break;
            }
        }

        while (true) {

            System.out.println("체크아웃 날짜를 입력해주세요.(YYYYMMDD)");
            System.out.print(">>");
            checkOut = sc.nextLine();
            checkOut = checkOut.trim();
            if (checkOut.equals('q')) {
                return;
            } else if (checkOut.length() != 8) {
                System.out.println("날짜는 YYYYMMDD 형식으로 입력해야합니다.");
                continue;
            } else if (!checkOut.matches("\\d+")) { // date가 숫자가 아닐 시
                System.out.println("날짜는 YYYYMMDD 형식의 숫자로 입력해야합니다.");
                continue;
            }

            int num1 = Integer.parseInt(checkIn);
            int num2 = Integer.parseInt(checkOut);

            if (num1 > num2) {
                System.out.println("체크인보다 이전 날짜는 체크아웃 날짜로 지정할 수 없습니다.");
                System.out.println("체크인은 " + checkIn + "입니다.");
                continue;
            } else {
                break;
            }
        }


        System.out.println("객실 리스트");
//        ArrayList<RoomInfo> temproom = new ArrayList<RoomInfo>();
        System.out.println("1호실 " + "20000");
        System.out.println("2호실 " + "40000");
        System.out.println("3호실 " + "30000");
        System.out.println("4호실 " + "50000");

//        boolean invalid_room = true;
        while (true) {
            System.out.println("머물고자하는 객실을 선택해주세요.");
            System.out.print(">>");
            String input = sc.nextLine().trim();
            if (input.equals('q')) {
                return;
            }
            if (!input.matches("\\d+")) {
                System.out.println("숫자만 입력해주세요.");
                continue;
            }
            myroomNum = Integer.parseInt(input);
            if (myroomNum <= 0 || myroomNum > rooms.size()) {
                System.out.println("해당 객실은 존재하지 않습니다.");
                continue;
            }
            for (int i = 0; i < rooms.size(); i++) {
                if (myroomNum == rooms.get(i).roomNum) {
                    int user_checkin = Integer.parseInt(checkIn);
                    int user_checkout = Integer.parseInt(checkOut);
                    int room_checkin, room_checkout;
                    if (!rooms.get(i).getcheckIn().equals("X") && !rooms.get(i).getcheckOut().equals("X")) {
                        room_checkin = Integer.parseInt(rooms.get(i).getcheckIn());
                        room_checkout = Integer.parseInt(rooms.get(i).getcheckOut());
                    } else {
                        continue;
                    }
                    if (user_checkin >= room_checkin && user_checkin <= room_checkout && user_checkout >= room_checkout) {
                        System.out.println("이 객실은 다른 회원이 이 기간에 이미 예약했음");
                        return;
                    }
                    if (user_checkin <= room_checkin && user_checkout >= room_checkin && user_checkin <= room_checkout && user_checkout >= room_checkout) {
                        System.out.println("이 객실은 다른 회원이 이 기간에 이미 예약했음");
                        return;
                    }
                    if (user_checkin <= room_checkin && user_checkout >= room_checkin && user_checkout <= room_checkout) {
                        System.out.println("이 객실은 다른 회원이 이 기간에 이미 예약했음");
                        return;
                    }
                    if (user_checkin >= room_checkin && user_checkin <= room_checkout && user_checkout >= room_checkin && user_checkout <= room_checkout) {
                        System.out.println("이 객실은 다른 회원이 이 기간에 이미 예약했음");
                        return;
                    }
                }
            }
            break;
//            invalid_room = false;

//            for (int j = 0; j < temproom.size(); j++) {
//                if (myroomNum == temproom.get(j).roomNum) {
//                    invalid_room = false;
//                    break;
//                }
//            }

        }
        while (true) {
            System.out.println("인원을 선택해주세요.");
            System.out.print(">>");
            String input = sc.nextLine();
            if (input.equals('q')) {
                return;
            }
            if (!input.matches("\\d+")) {
                System.out.println("숫자만 입력해주세요.");
                continue;
            }
            personNum = Integer.parseInt(input);
            if (personNum <= 0 || personNum > 9) {
                System.out.println("한 객실의 한도인원은 1~9인 입니다.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("결제하시겠습니까? (Y/N)\n>>");
            choose = sc.nextLine();
            int tmp = 0;
            if (choose.equals("Y")) {
                for (int i = 0; i < rooms.size(); i++) {
                    if (rooms.get(i).roomNum == myroomNum && rooms.get(i).getPhoneNum().equals("X")) {
                        rooms.get(i).checkIn = checkIn;
                        rooms.get(i).checkOut = checkOut;
                        rooms.get(i).personNum = personNum;
                        rooms.get(i).PhoneNum = user.getPhoneNum();
                        rooms.get(i).userName = user.getName();
                        tmp = rooms.get(i).price;
                    }
                }
                RoomInfo newroom = new RoomInfo(myroomNum, "X", "X", "X", "X", 0, tmp, false);
                rooms.add(newroom);
                break;
            } else if (choose.equals("N") || choose.equals("q")) {
                return;
            } else {
                System.out.println("다시 올바르게 입력해주세요.");
                continue;
            }
        }
        toCsv();
    }


    public void check_Reservation() {
        boolean reserve = false;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).PhoneNum.equals(user.getPhoneNum())) {
                reserve = true;
            }
        }
        if (!reserve) {
            System.out.println("사용자님의 예약이 존재하지 않습니다.");
        } else {
            while (true) {
                ArrayList<RoomInfo> tmprooms = new ArrayList<>();
                System.out.println("<예약확인>");
                System.out.println("---------------");
                System.out.println("예약 내역 출력");
                for (int i = 0; i < rooms.size(); i++) {
                    if (rooms.get(i).PhoneNum.equals(user.getPhoneNum())) {
                        RoomInfo tmp = rooms.get(i);
                        System.out.println(rooms.get(i).checkIn + " ~ " + rooms.get(i).checkOut);
                        System.out.println(rooms.get(i).roomNum + "호 " + rooms.get(i).userName);
                        System.out.println("인원 : " + rooms.get(i).personNum + "명");
                        System.out.println("금액 : " + rooms.get(i).price);
                        System.out.println("---------------");
                        tmprooms.add(tmp);
                    }
                }
                Scanner sc = new Scanner(System.in);
                System.out.println("1) 예약 취소");
                System.out.println("2) 돌아가기");
                System.out.println("---------------");
                try {
                    System.out.print(">>");
                    String choice = sc.nextLine().trim();
                    switch (choice) {
                        case "1":
                            System.out.print("몇번째 내역을 취소하시겠습니까?\n>> ");
                            int choose = sc.nextInt();
                            for (int i = 0; i < rooms.size(); i++) {
                                if (rooms.get(i).equals(tmprooms.get(choose-1))) {
//                                    rooms.get(i).checkIn = "X";
//                                    rooms.get(i).checkOut = "X";
//                                    rooms.get(i).personNum = 0;
//                                    rooms.get(i).PhoneNum = "X";
//                                    rooms.get(i).userName = "X";
                                    rooms.remove(rooms.get(i));
                                    toCsv();
                                }

//                                boolean flag = false;
//                                for (int j = rooms.size() - 1; j >= 0; j--) {
//                                    if (rooms.get(i).getroomNum() == rooms.get(j).getroomNum()) {
//                                        if(rooms.get(i).checkIn.equals("X"))
//                                            rooms.remove(rooms.get());
//                                        else
//                                            rooms.remove(rooms.get(j));
//                                        toCsv();
//                                        flag = true;
//                                        break;
//                                    }
//                                }
//
//                                if (flag) {
//                                    break;
//                                }

                            }

                            System.out.println("성공적으로 취소되었습니다.");




                            toCsv();
                            return;

                        case "2":
                            System.out.print("예약 메뉴로 돌아가시겠습니까? (Y/N)\n>>");
                            String input = sc.nextLine();
                            if (input.equals("Y")) {
                                return;
                            } else if (input.equals("N")) {
                                break;
                            } else {
                                System.out.println("올바르게 입력해주세요");
                            }
                        default:
                            System.out.println("1,2 숫자를 입력해주세요.");
                    }
                } catch (NumberFormatException E) {
                    System.out.println("올바른 형식으로 입력하세요!");
                }
            }
        }


    }

    public void toCsv() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            ArrayList<RoomInfo> csvarr = new ArrayList<>();
            for(int i=0;i<rooms.size();i++){
                csvarr.add(rooms.get(i));
            }
            Comparator<RoomInfo> roomComparator = new Comparator<RoomInfo>() {
                @Override
                public int compare(RoomInfo o1, RoomInfo o2) {
                    return Integer.compare(o1.roomNum, o2.roomNum);
                }
            };

            Collections.sort(csvarr,roomComparator);
            for (RoomInfo r : csvarr) {
                writer.write(r.getroomNum() + "," + r.getuserName() + "," + r.getPhoneNum() + "," + r.getcheckIn() + "," + r.getcheckOut() + "," + r.getpersonNum() + "," + r.getprice() + "," + r.getisAccept() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fromCsv() {
        BufferedReader br;

        try (FileReader fileReader = new FileReader(filename)) {
            br = Files.newBufferedReader(Paths.get(filename));
            String line = "";

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                String[] array = line.split(",");

                RoomInfo room = new RoomInfo(Integer.parseInt(array[0]), array[1], array[2], array[3], array[4], Integer.parseInt(array[5]), Integer.parseInt(array[6]), Boolean.parseBoolean(array[7]));
                rooms.add(room);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public String toString() {
        return "RoomInfo{" +
                "checkIn='" + checkIn + '\'' +
                ", userName='" + userName + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", PhoneNum='" + PhoneNum + '\'' +
                ", personNum=" + personNum +
                ", price=" + price +
                ", roomNum=" + roomNum +
                ", isAccept=" + isAccept +
                ", rooms=" + rooms +
                ", filename='" + filename + '\'' +
                '}';
    }
}
