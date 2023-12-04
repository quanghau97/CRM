package Date;

public class Date {
	public String changeFormatDate(String date, String style) {
        switch (style) {
            case "/":
                String[] strings1 = date.split("-");
                return strings1[2] + "/" + strings1[1] + "/" + strings1[0];
            case "-":
                String[] strings2 = date.split("/");
                return strings2[2] + "-" + strings2[1] + "-" + strings2[0];
        }
        return "";
    }

}
