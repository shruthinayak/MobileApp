package hello;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Nomadss extends MIDlet implements CommandListener, ItemCommandListener, ItemStateListener {

    canv canv1 = new canv();
  //  canvppl canvppl = new canvppl();
    Font f3 = Font.getFont(Font.FONT_STATIC_TEXT, Font.STYLE_BOLD, Font.SIZE_MEDIUM);//List of hospitals will be displayed in this font
    //Commands Used
    Command ok = new Command("OK", Command.OK, 0);//the fourth parameter is the number of command-line arguments
    Command exit = new Command("Exit", Command.EXIT, 0);
    Command next = new Command("Next", Command.OK, 0);
    Command back = new Command("Back", Command.BACK, 0);
    Command mainMenu = new Command("MainMenu", Command.BACK, 0);
    Command Cre = new Command("Credits", Command.OK, 0);
    Command abt = new Command("About", Command.OK, 0);
    Command search = new Command("Search", Command.OK, 0);
    Command signin = new Command("Sign in", Command.OK, 0);
    Command register = new Command("Register", Command.OK, 0);
    Command create = new Command("Create", Command.OK, 0);
    Command rev = new Command("Review", Command.OK, 0);
    Command signout = new Command("Sign Out", Command.OK, 0);
    Command selectCommand = new Command("Select", Command.OK, 0);//Having a select button with the operation 'ok'
    Command done = new Command("Done", Command.OK, 0);
    Command help = new Command("Help Desk", Command.OK, 0);
    Command backtoprofile = new Command("Go back to profile", Command.BACK, 0);
    Command backtotopfive=new Command("Back to Top 5", Command.BACK, 0);
    Command backtosearch=new Command("Back to Search", Command.BACK, 0);
    FoundPlace universal;
    //************************************************************************************
    //Database
    Categor[] cat = new Categor[2];
    Places[] plac = new Places[7];
    Bazaars[] bazz = new Bazaars[5];
    Area[] a = new Area[104];
    Ccd[] ccd = new Ccd[58];
    CornerHouse[] CH = new CornerHouse[13];
    Dominos[] dom = new Dominos[39];
    Kfc[] kfc = new Kfc[39];
    McD[] mcD = new McD[20];
    BigBazaar[] big = new BigBazaar[13];
    ShoppingMalls[] shop = new ShoppingMalls[20];
    StarBazaar[] str = new StarBazaar[4];
    PizzaHut[] PH = new PizzaHut[28];
    Spar[] sp = new Spar[4];
    PizzaCorner[] PC = new PizzaCorner[26];
    Spencer[] spn = new Spencer[34];
    Hangout[] h = new Hangout[5];

    {
        for (int i = 0; i < 5; i++) {
            h[i] = new Hangout();
        }
    }
    Top_areas[] t = new Top_areas[5];

    {
        for (int i = 0; i < 5; i++) {
            t[i] = new Top_areas();
        }
    }
    FoundPlace[] p = new FoundPlace[5];

    {
        for (int i = 0; i < 5; i++) {
            p[i] = new FoundPlace();
        }
    }


    //****************************************************************************************
    Display d = Display.getDisplay(this);
    String search_id = new String();
    //***************************************************************************************
    //Choice Groups
    ChoiceGroup choice = new ChoiceGroup("", Choice.EXCLUSIVE);
    ChoiceGroup category = new ChoiceGroup("", Choice.EXCLUSIVE);
    ChoiceGroup eatchoice = new ChoiceGroup("", Choice.EXCLUSIVE);
    ChoiceGroup rate = new ChoiceGroup("", Choice.EXCLUSIVE);
    ChoiceGroup hangout_choice = new ChoiceGroup("", Choice.EXCLUSIVE);
    ChoiceGroup top_area_choice = new ChoiceGroup("", Choice.EXCLUSIVE);
    ChoiceGroup top_place_choice = new ChoiceGroup("", Choice.EXCLUSIVE);
    //***************************************************************************************
    // ALERTS !!
    Alert login_alert = new Alert("Fill in both" + " \nthe fields ");
    Alert reg_alert = new Alert("Fill in all" + " \nthe fields " + "\n correctly");
    Alert place_Alert = new Alert("Place Not Found !" +"\n"+ "Try Again !");
    Alert success = new Alert("Congrats" + "\n" + "Account created");
    Alert failure = new Alert("Sorry !"+"\n"+"Username"+"\n"+"not available!"+"\n"+"Try Again");
    Alert Added = new Alert("Added to Hangout!");
    Alert suc = new Alert("Added to your hangouts !");
    Alert thanku = new Alert("Thank You for" +"\n"+ "your valuable"+"\n"+" Comment");
    Alert already = new Alert("Already present"+"\n"+ "in your"+"\n"+" Hangout List");
    Alert exist = new Alert("Username doesnot"+"\n"+" exist");
    Alert enter = new Alert("Please enter"+"\n"+" both the "+"\n"+"name and area");
    Alert SelectHang = new Alert("Select Hangout");
    Alert nomatch = new Alert("Passwords"+"donot match");
    //***************************************************************************************
    String found_number = new String();
    //*****************************************
    //***************************************************************************************
    //Main
    Form Main = new Form("Welcome");
    StringItem skip;
    //Credit Form
    Form Credits = new Form("CREDITS");
    Form About = new Form("About");
    Form Help = new Form("Help");
    //Login Screen
    Form login = new Form("Welcome Nomad");
    TextField username = new TextField("Nomad Name", null, 30, TextField.ANY);
    TextField pwd = new TextField("Password", null, 30, TextField.PASSWORD);
    TextField comment = new TextField("Comment", null, 120, TextField.ANY);
    //Registration Form
    Form registration = new Form("Wanna Be a Nomad?");
    TextField first_name = new TextField("First Name", "", 30, TextField.ANY);
    TextField lastname = new TextField("Last Name", "", 30, TextField.ANY);
    TextField username_r = new TextField("NomadName", "", 30, TextField.ANY);
    TextField pwd_r = new TextField("Password", "", 30, TextField.PASSWORD);
    TextField con_pwd_r = new TextField("Confirm Password", "", 30, TextField.PASSWORD);
    // ENTERING THE PROFILE
    Form hangout = new Form("Your Nomadic Life");
    StringItem search_but;
    //Find your destination
    Form fifth = new Form("At your service");
    TextField Name = new TextField("Name of the place", "", 30, TextField.ANY);
    TextField area = new TextField("Area", "", 30, TextField.ANY);
    Form top_five_area = new Form("Top Five Areas");
    Form top_five_place = new Form("Top Five Names");
    Form place = new Form("Found a Right Choice?");
    StringItem AddToHangout;
    // Review
    Form review = new Form("Reviews");
    StringItem call;

    public static String replaceAll(String text, String searchString, String replacementString) {
        StringBuffer sBuffer = new StringBuffer();
        int pos = 0;
        while ((pos = text.indexOf(searchString)) != -1) {
            sBuffer.append(text.substring(0, pos) + replacementString);
            text = text.substring(pos + searchString.length());
        }
        sBuffer.append(text);
        return sBuffer.toString();
    }

    public void startApp() {

        skip = new StringItem("", "Skip", StringItem.BUTTON);
        skip.setDefaultCommand(ok);
        skip.setItemCommandListener((ItemCommandListener) this);

        Main.append(skip);
        Main.append("\n           Welcome");
        Main.addCommand(abt);
        Main.addCommand(help);
        Main.addCommand(Cre);



        Credits.append("                DEVELOPERS");
        Credits.append("\n"+"\u2022" + " RESHMA NAZEER HUSSAIN");
        Credits.append("\n"+"\u2022" + " SHOBITHA SHETTY");
        Credits.append("\n"+"\u2022" + " SHRUTHI NAYAK");
        Credits.append("Mail us at :      ");
        Credits.append("nom-ads@googlegroups.com");
        Credits.addCommand(back);

        About.append("NOM-ads 1.0 helps you to search,locate,rate and comment fro your favourite places .Now that sounds like fun ,doesn't it? The user is given an option of choosing from 2 categories namely,eatables and shopping. Each of these choices will in turn give you options of famous places in various areas to select from.");
        About.addCommand(back);

        Help.append("\u2022" +"\n"+ "Eatables: The fields provided under this section are Cafe Coffee Day, Mc Donald,Pizza Hut,Pizza Corner, Corner House, Dominoes KFC. If the user wants to search for ,say, Pizza Hut in a particular area ,then he can do so by entering both the name and place he intends to know .The user will be provided with name ,location, address ,phone number of that place. Along with this he/she would be provided with a call button which allows you to directly make a call to that place. ");
        Help.append("\u2022" + "\n"+"Shopping :If the user wants to search for particular shopping destination ,then he can do so by entering both the name and place he intends to know .The user will be provided with name ,location, address ,phone number of that place. Along with this he/ she would be provided with a call button which allows you to directly make a call to that place.");
        Help.append("\u2022" +"\n"+ "Review:Wanna know what others think about that place? Want to tell others how  you felt about that place. Then review section is the place which gives the ratings and comments for that place. Provides an oppurtunity for a customer to rate a place based on his satisfaction - Happy ,Okay or buhoo bad.. Speak your hearts out.");
        Help.append("\u2022" + "\n"+"ADDtoHangouts: Like some place very much? Feel its the place for you to go eat,shop, hangout with your friends? Then add it to your hangouts .This gives you an easy access to the place you like the most .");
        Help.addCommand(back);
        //login screen
        login.append(username);
        login.append(pwd);
        login.append("Dont have an Account?" + "\n" + "Then this is the time" + "\n" + "for you to register !!");
        login.addCommand(signin);
        login.addCommand(register);

        //parse(login_details);

        //Registration Screen
        registration.append(first_name);
        registration.append(lastname);
        registration.append(username_r);
        registration.append(pwd_r);
        registration.append("Minimum 7 characters");
        registration.append(con_pwd_r);
        registration.addCommand(back);
        registration.addCommand(create);

        fifth.append(category);
        fifth.append(Name);
        fifth.append(eatchoice);
        fifth.append(area);
        fifth.append(choice);
        fifth.addCommand(back);
        fifth.addCommand(search);

        review.addCommand(backtoprofile);
        a[0] = new Area("Adugodi");
        a[1] = new Area("Airport Road");
        a[2] = new Area("Bagmane Tech Park");
        a[3] = new Area("Banashankari");
        a[4] = new Area("Banashankari III stage");
        a[5] = new Area("Basavanagudi");
        a[6] = new Area("Basaweshwar Nagar");
        a[7] = new Area("BEL Road");
        a[8] = new Area("Bennigana Halli");
        a[9] = new Area("Brigade Road");
        a[10] = new Area("CMH Road");
        a[11] = new Area("Coles Road");
        a[12] = new Area("Commercial Street");
        a[13] = new Area("Domlur");
        a[14] = new Area("Frazer Town");
        a[15] = new Area("Hosur Main Road");
        a[16] = new Area("ITPL");
        a[17] = new Area("Jayanagar");
        a[18] = new Area("Kamanahalli");
        a[19] = new Area("Kanakpura");
        a[20] = new Area("Koramangala");
        a[21] = new Area("Kasturba Road");
        a[22] = new Area("Cunningham Road");
        a[23] = new Area("RT Nagar");
        a[24] = new Area("Sadashivnagar");
        a[25] = new Area("Vijayanagar");
        a[26] = new Area("Bannerghatta Road");
        a[27] = new Area("Salarpuria");
        a[28] = new Area("Banaswadi");
        a[29] = new Area("Marathalli");
        a[30] = new Area("BTM Layout");
        a[31] = new Area("Electronic City");
        a[32] = new Area("Peenya");
        a[33] = new Area("HSR Layout");
        a[34] = new Area("Bellandur");
        a[35] = new Area("Victoria Road");
        a[36] = new Area("Brookefield");
        a[37] = new Area("Cox Town");
        a[38] = new Area("Hebbal");
        a[39] = new Area("Garuda Mall");
        a[40] = new Area("Indiranagar");
        a[41] = new Area("Ramnagaram");
        a[42] = new Area("Kathriguppe");
        a[43] = new Area("MG Road");
        a[44] = new Area("Malleswaram");
        a[45] = new Area("Old Madras Road");
        a[46] = new Area("Rajarajeshwari Nagar");
        a[47] = new Area("Sarjapur");
        a[48] = new Area("Wonder La");
        a[49] = new Area("Whitefield");
        a[50] = new Area("Vidyanarayanapura");
        a[51] = new Area("Madivala");
        a[52] = new Area("Mathikere");
        a[53] = new Area("Murugeshpalya");
        a[54] = new Area("Trinity Circle");
        a[55] = new Area("Varthur");
        a[56] = new Area("Madras Bank Road");
        a[57] = new Area("HRBR Layout");
        a[58] = new Area("Jayanagar 5th Block");
        a[59] = new Area("Jayanagar 9th Block");
        a[60] = new Area("Kasturinagar");
        a[61] = new Area("CV Raman nagar");
        a[62] = new Area("Residency Road");
        a[63] = new Area("Richard's Town");
        a[64] = new Area("Sheshadripuram");
        a[65] = new Area("Yelahanka");
        a[66] = new Area("Ascendas Park Square");
        a[67] = new Area("yelahanka satellite town");
        a[68] = new Area("Rajajinagar");
        a[69] = new Area("Richmond Town");
        a[70] = new Area("JP Nagar");
        a[71] = new Area("Hoysalanagar");
        a[72] = new Area("Uttarahalli");
        a[73] = new Area("Srirampuram");
        a[74] = new Area("Kalyanagar");
        a[75] = new Area("Byatarayanapura");
        a[76] = new Area("KH Road");
        a[77] = new Area("Sanjaya Nagar");
        a[78] = new Area("South End street");
        a[79] = new Area("Mahalakshmipuram");
        a[80] = new Area("Padmanabha Nagar");
        a[81] = new Area("Vyalikaval");
        a[82] = new Area("Hesaraghatta");
        a[83] = new Area("Museum Road");
        a[84] = new Area("Devanahalli");
        a[85] = new Area("Vasanth Nagar");
        a[86] = new Area("Tilak Nagar");
        a[87] = new Area("Vittal Mallaya Road");
        a[88] = new Area("Sampangirama Nagar");
        a[89] = new Area("Mudugere");
        a[90] = new Area("Marathahalli Road");
        a[91] = new Area("Nagarbhavi");
        a[92] = new Area("Ulsoor");
        a[93] = new Area("Kengeri Satellite town");
        a[94] = new Area("Vidyaranya Pura");
        a[95] = new Area("Richmond Road");
        a[96] = new Area("Bilekahalli");
        a[97] = new Area("Jayanagar 4th Block");
        a[98] = new Area("Sarjapura Road");
        a[99] = new Area("Mahadevapura");
        a[100] = new Area("Gandhi Nagar");
        a[101] = new Area("West of Chord Road");
        a[102] = new Area("Kodihalli");
        a[103] = new Area("Mysore Road");



        cat[0] = new Categor("Eatables");
        cat[1] = new Categor("Shopping");

        plac[0] = new Places("Cafe Coffee Day");
        plac[1] = new Places("Corner House");
        plac[2] = new Places("Dominos");
        plac[3] = new Places("KFC");
        plac[4] = new Places("McDonalds");
        plac[5] = new Places("Pizza Corner");
        plac[6] = new Places("Pizza Hut");

        bazz[0] = new Bazaars("Big Bazaar");
        bazz[1] = new Bazaars("Shopping Malls");
        bazz[2] = new Bazaars("Spar");
        bazz[3] = new Bazaars("Spencers");
        bazz[4] = new Bazaars("Star Bazaar");


        spn[1] = new Spencer("MG Road", "08041121561", "86, Spencer's Tower, M.G. Road, Bangalore-1");
        spn[2] = new Spencer("BTM Layout", "08026685514", "766, 16th Main, B.T.M. 2nd Stage, Bangalore-76 ");
        spn[3] = new Spencer("Frazer Town", "08025807771", "2&2/1&3, Mosque Road, Frazer Town, Bangalore-5");
        spn[4] = new Spencer("Airport Road", "08025271388", "41, HAL Airport Main Road, Konena Agarahara, Bangalore-17");
        spn[5] = new Spencer("Sarjapur", "Kaikondana Halli, Sarjapura Road, Bangalore-102");
        spn[6] = new Spencer("Banashankari III Stage", "08026790668", "131, Kathriguppe Main Road, Banashankari 3rd Stage, Bangalore-68");
        spn[7] = new Spencer("Banashankari", "08065375202", "649, 2nd Cross, 2nd Main, Sri Vidhyanagar, Banashankari 3rd Stage, Bangalore-85");
        spn[8] = new Spencer("Banashankari III Stage", "08026421146", "83, Ittimadu Main Road, 3rd Phase, Banashankari 3rd Stage, Bangalore-85");
        spn[9] = new Spencer("Basavanagudi", "08026620273", "60, Gandhi Bazaar Road, Basavanagudi, Bangalore-4");
        spn[10] = new Spencer("Basaweshwar Nagar", "0802312778", "73, West of Chord Road, 2nd Stage, Basaveshwara Nagar, Bangalore-3");
        spn[11] = new Spencer("Brookefield", "08041693622", "1, ITPL Main Road, Kundana Halli, Brookefield, Bangalore-37");
        spn[12] = new Spencer("HSR Layout", "08025722430", "3, 24th Main Road, Parangipalya Village, H.S.R. Layout, Bangalore-34");
        spn[13] = new Spencer("Indiranagar", "08040365555", "8, 80 Feet Main Road, HAL 2nd Stage, Indira Nagar, Bangalore-8");
        spn[14] = new Spencer("JP Nagar", "08022451933", "147/10-2, 9th Cross, Sarakki Village, J.P. Nagar 1st Phase, Bangalore-78");
        spn[15] = new Spencer("JP Nagar", "08026492566", "8, 24th Main Road, JP Nagar 5th phase, Bangalore-78");
        spn[16] = new Spencer("JP Nagar", "08041536515", "70, GR Plaza, Kanakapura Main Road. JP Nagar 6th Phase, Bangalore-78");
        spn[17] = new Spencer("JP Nagar", "08026852240", "31, Kothanoor Main Road, JP Nagar 7th Phase, Bangalore-78");
        spn[18] = new Spencer("Jayanagar", "08041323078", "74, Elelphant Rock Road, Jayanagar 3rd Block, Bangalore-11");
        spn[19] = new Spencer("Kasturinagar", "08025422745", "DC-201, RMR Sunshine 1, east of NGEF, Kasthuri Nagar, Bangalore-43");
        spn[20] = new Spencer("South End Street", "08022372205", "1, South End Street, Kumara Krupa, Bangalore-1");
        spn[21] = new Spencer("Mahalakshmipuram", "9980945105", "WOC Road, 2nd Stage, Mahalakshmipuram, Bangalore-86");
        spn[22] = new Spencer("Mathikere", "08023455506", "52, MS Ramaiah Plaza, MS Ramaiah Layout, Gokul Extension, Mathikere, Bangalore-54");
        spn[23] = new Spencer("Padmanabha Nagar", "08026392578", "40, Subramanyapura Main Road, Chikkalasandra, Padmanabha Nagar, Bangalore-70");
        spn[24] = new Spencer("RT Nagar", "08023430508", "9, Dinnur Main Road, R.T. Nagar, Bangalore-32");
        spn[25] = new Spencer("Rajajinagar", "08023100010", "Old 369/B, New 2, Rajaji Nagar 4th Block, Bangalore-10");
        spn[26] = new Spencer("Rajajinagar", "08023147143", "1/1, Radhakrishna Complex, Dr. Rajkumar Road, Rajaji Nagar 6th Block, Bangalore-10");
        spn[27] = new Spencer("Sanjaya Nagar", "08023513262", "Old 252/2, New 1, 3rd Main Road, D Rajgopal Road, GMR Layout, Geddalahalli, Sanjaya Nagar, Bangalore-94");
        spn[28] = new Spencer("Sarjapur", "08025740139", "223, Greenglen Layout, Belandur Village, Sarjapura Road, Bangalore-68");
        spn[29] = new Spencer("Vyalikaval", "08023313513", "75/11, 2nd Main Road, Vyalikaval, Bangalore-3");
        spn[30] = new Spencer("Banashankari", "08026794440", "1, Saroj Complex, Ground Floor, 30th Main Road, Banashankari 3rd Stage, Bangalore-85");
        spn[31] = new Spencer("Koramangala", "08025522053", "6, 7th Main, 80 Feet Road, Koramangala 1st Block, Bangalore-34");
        spn[32] = new Spencer("Sarjapur", "08025740290", "629, 18/2A, GRS Tower, Bellandur Gate, Ambalipura Village, Sarjapura Ring Road, Bangalore-102");
        spn[33] = new Spencer("Hesaraghatta", "08040365555", "83/4, Cave Temple Road, Doddamma Layout, Bannerghatta Road, Hesaraghatta, Bangalore-76");


        ccd[0] = new Ccd("Sampangirama Nagar", "Sampangirama Nagar, Bangalore");
        ccd[1] = new Ccd("Indiranagar", "CCD Odyssey, 100ft road, Indiranagar, Bangalore", "08060603333");
        ccd[2] = new Ccd("JP Nagar", "Outer Ring Road, Phase II, JP Nagar,Bangalore");
        ccd[3] = new Ccd("JP Nagar", "Bangalore Central Mall, Plot no 45/1, 45/2, 45th Cross, JP Nagar 2nd Phase, Bangalore", "08032430063");
        ccd[4] = new Ccd("Koramangala", "Vajram Complex, 100 feet Road, Koramangala, Bangalore");
        ccd[5] = new Ccd("Koramangala", "Vajram Complex, 100 feet Road, Koramangala, Bangalore");
        ccd[6] = new Ccd("Museum Road", "No 318, Raheja Chamber, No 12, Museum Road, Museum Road,Bangalore-560001", "08025589581");
        ccd[7] = new Ccd("Vyalikaval", "Vaiyyalikaval, Palace Guttahalli,Bangalore", "08032489316");
        ccd[8] = new Ccd("Richmond Town", "Richmond Road, Richmond Town, Bangalore");
        ccd[9] = new Ccd("Vasanth Nagar", "Opp to Mount Carmel College, Vasanth Nagar, Bangalore", "08064528293");
        ccd[10] = new Ccd("Vittal Mallaya Road", "Coffee Day Square, 23/2, Opp UB City,Vittal Mallaya Road, Bangalore-560001", "08040012345");
        ccd[11] = new Ccd("Tilak Nagar", "4T Block, Tilak Nagar,Bangalore", "08032486248");
        ccd[12] = new Ccd("Mudugere", "67th Km Bangalore, Mysore Highway, Mudugere, Bangalore", "9243612962");
        ccd[13] = new Ccd("Electronic City", "Counter-1, Electronic City, Hosur Road, Bangalore", "08032486294");
        ccd[14] = new Ccd("Residency Road", "Bangalore Club,opp chenseller hotel, Residency Road, Bangalore");
        ccd[15] = new Ccd("Brigade Road", "HM Towers, 58, Brigade Road, Bangalore");
        ccd[16] = new Ccd("Marathahalli Road", "Marathahalli Road, Opp To Salapuria Hallmark, Next To Country Club, Bangalore", "08065391172");
        ccd[17] = new Ccd("Whitefield", "G R Tech Park, Salarpuria Campus, ITPL, Whitefield Area, Bangalore");
        ccd[18] = new Ccd("Residency Road", "Opp Mayo Hall, Residency Road, Bangalore");
        ccd[19] = new Ccd("Devanahalli", "BIAL, Devanahalli, Bangalore", "9243611330");
        ccd[20] = new Ccd("Marathalli", "Rmz Ecospace-2, Near Pratham Motors, Marathalli, Bangalore", "08064529986");
        ccd[21] = new Ccd("Yelahanka", "Narasipura Village, Phase 3, Vidyaranyapura Layout, Yelahanka, Bangalore");
        ccd[22] = new Ccd("Nagarbhavi", "7, 1st Blk, 2nd Stage, Nagarbhavi Layout, Bangalore");
        ccd[23] = new Ccd("Banaswadi", "NO4 DC-202, 2nd Main Road, Kasturinagar, Banaswadi, Bangalore");
        ccd[24] = new Ccd("Old Madras Road", "Big Bazaar, Old Madras Road, Bangalore", "9343712841");
        ccd[25] = new Ccd("Bannerghatta Road", "Apollo Hospital-@154/11, Opp.Iim-B, Bannergatta Main Road, Bangalore");
        ccd[26] = new Ccd("Malleswaram", "Ground Floor, Mantri Square, Malleshwaram, Bangalore", "9343712801");
        ccd[27] = new Ccd("Frazer Town", "ADAMS CORNER, No. 37, Coles Road, Frazer Town, Bangalore");
        ccd[28] = new Ccd("Whitefield", "Innovative Building, Tech Park Health, Club, ITPL, Whitefield, Bangalore");
        ccd[29] = new Ccd("Airport Road", "S-1A, Gem Wellington, No. 102/3, Airport Road, Bangalore");
        ccd[30] = new Ccd("Indiranagar", "612, 12th Main, Krishna Enclave, Indira Nagar, Bangalore", "08060603333");
        ccd[31] = new Ccd("Whitefield", "Sigmatech, Beta Tower, No.7, WhiteField Main Road, Bangalore");
        ccd[32] = new Ccd("Rajarajeshwari Nagar", "Arch Mall, Near Rajarajeshwarinagar Arch, Opp., The Club, Mysore Road, Bangalore", "08064529984");
        ccd[33] = new Ccd("Kodihalli", "39 Ground Floor, N-type Sector 11, Kodihalli, Jeevan Beema Nagar, Bangalore");
        ccd[34] = new Ccd("Frazer Town", "Iqbal Plaza, # 36, Millers Road, Benson Town, Frazer Town Area, Bangalore", "08064540061");
        ccd[35] = new Ccd("Richmond Town", "G02A- Ground Floor, Garuda Mall, Magrath Road,Richmond Town,Bangalore");
        ccd[36] = new Ccd("Ulsoor", "9, Tank Road, Ulsoor (North side of Ulsoor Lake), Bangalore", "08032489321");
        ccd[37] = new Ccd("Indiranagar", "537, Robby Arcade, C.M.H Road, Indira Nagar, Bangalore");
        ccd[38] = new Ccd("Marathalli", "Cisco system India Pvt Ltd, Sez Cessna, Business Park, Kadubenahalli Village, Sarjapur Ring road, Marathahalli, Bangalore");
        ccd[39] = new Ccd("Kengeri Satellite town", "1250/15/115, Kengeri Satellite Town, 1st Main, Bangalore", "08032471189");
        ccd[40] = new Ccd("HSR Layout", "No. 2614, Sector - 1, 27th Main, Opp CPW Complex, HSR Layout, Bangalore");
        ccd[41] = new Ccd("Basavanagudi", "SPC Complex, Shop No.1, Bull Temple Road, Opp BSNL Office, Basavanagudi, Bangalore");
        ccd[42] = new Ccd("BTM Layout", "BTM Layout");
        ccd[43] = new Ccd("Jayanagar", "32nd Cross Rd, 7th Block, JayanagarBangalore-560085", "08064528292");
        ccd[44] = new Ccd("Vijayanagar", "Next to Shanti Sagar, woc Road, Vijaynagar, Bangalore");
        ccd[45] = new Ccd("Rajajinagar", "1691, Prakash Nagar, Dr. Rajkumar Road, Rajajinagar 3rd stage, Bangalore");
        ccd[46] = new Ccd("Airport Road", "HAL Airport[Old Airport], Parking Lot, Hal, Bangalore");
        ccd[47] = new Ccd("Commercial Street", "1/23, First Floor, Commercial Street, Bangalore", "08032472173");
        ccd[48] = new Ccd("Bennigana Halli", "B Channasandra, Bennigana Halli, Bangalore");
        ccd[49] = new Ccd("Banashankari", "Kathriguppe Main Road, Banashankari 3rd stage,Banashankari, Bangalore", "08064516796");
        ccd[50] = new Ccd("Vidyaranya Pura", "4 Block, Vidyaranya Pura, Bangalore", "08032486279");
        ccd[51] = new Ccd("Kalyanagar", "HRBR Layout 3rd Block, Kalyanagar, Bangalore", "08064516792");
        ccd[52] = new Ccd("Richmond Road", "89, Richmond circle, Next to Baldwin Women College, Richmond Road,Bangalore");
        ccd[53] = new Ccd("Ulsoor", "The Millenia Ground Floor, Tower C, No. 1&2 Bhaskaran Road, Ulsoor, Bengaluru");
        ccd[54] = new Ccd("Malleswaram", "C/o Bpcl, Shankars' Petrol bunk, 61/1, 8th Main, 19th Cross, Malleswaram, Bengalore");
        ccd[55] = new Ccd("Bilekahalli", "8 Main, Vijaya Bank Layout, Bilekahalli, Bangalore");
        ccd[56] = new Ccd("Jayanagar", "Garla Garnet, Ground Floor, 519/A, 9th Main, 4th Block, Jayanagar, Bangalore", "08064541179");
        ccd[57] = new Ccd("Sadashivnagar", "4, Ground Floor, Anaga Building New Bel Road, Sadashivanagar, Bangalore-560054", "08064516795");


        CH[0] = new CornerHouse("Airport Road", "Unit No. 25, Carlton Towers, No.1, Airport Road, Bangalore 560017", "08025216312");
        CH[1] = new CornerHouse("Madras Bank Road", "No.4, Airlines Hotel, Madras Bank Road, Bangalore 560009", "9845930618");
        CH[2] = new CornerHouse("BEL Road", "No. 66, 80 Ft. Road, Siddarth Complex, Opposite Ramaiah Hospital, New BEL Road, Bangalore 560094 ", "9845196610");
        CH[3] = new CornerHouse("Basavanagudi", "No. 10, Kanankapura Road, Basavanagudi, Bangalore", "08041699595");
        CH[4] = new CornerHouse("HRBR Layout", "No. 4 C, 614, 2nd Block, HRBR Layout, Bangalore 560043", "08065465551");
        CH[5] = new CornerHouse("Indiranagar", "3283, 12th Main, 8th Cross, Next to HDFC Bank, Indiranagar, Bangalore 560008", "9880846251");
        CH[6] = new CornerHouse("Jayanagar 5th Block", "Darkk Chocolat, 532, 36th Cross, 10th Main, 5th Block Jayanagar, Bangalore 560041", "08026653532");
        CH[7] = new CornerHouse("Jayanagar 9th Block", "No. 1225, 26th Main, Jayanagar 9th Block, Bangalore 560069  (Near Ragi Gudda Temple) ", "08026540925");
        CH[8] = new CornerHouse("Koramangala", "No. 363 & 364, 1st A Main, Koramangala 7th Block, Bangalore 560095 (Next to Sweet Chariot)", "9845923734");
        CH[9] = new CornerHouse("Kasturinagar", "# 1CC-201, 2nd Main Road, East of NGEF Layout, Kasturinagar, Bangalore 560043", "9686191973");
        CH[10] = new CornerHouse("Marathalli", "35/1 C, Munekolalu Village, Varthur Hobli, Bangalore-Varthur Road, Marathhalli, Bangalore 560037", "08028495444");
        CH[11] = new CornerHouse("Residency Road", "No. 45/3, G.K. Complex,  Residency Road Cross, Bangalore 560025", "9900545172");
        CH[12] = new CornerHouse("Richard's Town", "7/5, Clarke Road, Richard's Park, Richard's Town, Bangalore", "8123538810");

        dom[0] = new Dominos("AIRPORT ROAD", "SHOP NO. G-2-4, Next to TOTAL, SKYLARK PALAZZO, CORPORATION NO.98, MURUGESHPALAYA, AIRPORT ROAD, BANGALORE", "08025233391");
        dom[1] = new Dominos("ASCENDAS PARK SQUARE", "UNIT NO. 01-13, FIRST FLOOR, ASCENDAS PARK SQUARE MALL, INTERNATIONAL TECH PARK, BANGALORE");
        dom[2] = new Dominos("BANNERGHATTA ROAD", "GROUND FLOOR, GOWRY'sBUILDING NO. 16, DOOR NO.239/e-2/4, AREKERE GATE, BANERGHATTA MAIN ROAD, BANGALORE", "08026486401");
        dom[3] = new Dominos("BELLANDUR", "SOUL SPACE SPIRIT, 4TH FLOOR, FOOD COURT, OUTER RING ROAD, NEAR SARJAPURA BELLANDUR JUNCTION, BANGALORE");
        dom[4] = new Dominos("Banashankari", "NO-17, GF, 3RD PHASE, NEAR BIG BAZAR, 100FT RING ROAD, 6TH BLOCK, BANASHANKRI III STAGE, BANASHANKRI, BANGALORE", "08026699595");
        dom[5] = new Dominos("Basaweshwar Nagar", "GROUND FLOOR, SITE NO.354 AND 353/A, NEXT TO MORE, IV BlOCK, BASAVESWARANAGAR, BANGALORE", "08023230106");
        dom[6] = new Dominos("HOSUR Main ROAD", "SITE NO.1, SURVEY NO.56/17, FIRST FLOOR, KUDLU GATE, HONGASANGRA VILLAGE, BEGUR HOBLI, HOSUR ROAD, BANGALORE", "08042351412");
        dom[7] = new Dominos("BELLANDUR", "SRI GOWRIKSHNA ARCADE, SURVEY NO 78/5, BELLANDUR MAIN ROAD, OPP RAMA TEMPLE, BEHIND CENTRAL MALL, BANGALORE", "08025740888");
        dom[8] = new Dominos("Whitefield", "73/1,SHOP NO 31 THE ARCADE, BRIGADE METROPOLIS,GARUDACHARPALYA, MADHADEVPURA POST, WHITE FIELD MAIN ROAD, BANGALORE", "08022300222");
        dom[9] = new Dominos("BROOKEFIELD", "FIRST FLOOR, PLOT NO 7D, BROOKEFIELD ROAD, DODDANEKKUNDI VILLAGE, WHITEFIELD, BANGALORE", "08028542901");
        dom[10] = new Dominos("BTM LAYOUT", "No 67, 35th Main, 100 FT RING ROAD, BTM II STAGE, BANGALORE", "08026687722");
        dom[11] = new Dominos("BASaVANaGUDI", "GROUND FLOOR SITE NO.38, BULL TEMPLE ROAD(, BASAVEWARA TEMPLE RAOD), BASVANGUDI, BANGALORE", "08022412121");
        dom[12] = new Dominos("UTTARAHALLI", "KANDRA SPPHIRE, GROUND FLOOR 657,NEXT TO BRIGADE RESIDENCY APARTMENT, MAIN ROAD, CHIKKALLASANDRA, UTTARAHALLI HOBLI, BANGALORE", "08026613391");
        dom[13] = new Dominos("COLES ROAD", "GROUND FLOOR NO. 22/1, COLSE ROAD, OPPOSITE FATIMA SCHOOL, BANGALORE", "0802549958087");
        dom[14] = new Dominos("CUNNINGHAM Road", "37/2,CHICAGO AVENUE,CUNNGHAM ROAD, BANGALORE", "08022374591");
        dom[15] = new Dominos("CV RAMAN NAGAR", "GROUND FLOOR, WESTERN B PORTION, 91/1, NAGWARPALYA, KAGADASPURA MAIN ROAD, C.V.RAMAN NAGAR, KR PURAM HOBLI, BANGALORE", "08025342737");
        dom[16] = new Dominos("DOMLUR", "PYRAMID FOOD COURT, EMBASSY GOLFLING PARK, SURVEY NO. 7/1 AND 7/3, DOMLUR, BANGALORE", "0802535610");
        dom[17] = new Dominos("Hosur Main Road", "C/O INFOSYS TECHNOLOGY, PLOT NO.44, HOSUR ROAD, ELECTRONIC CITY, PHASE-1, BANGALORE", "08065733856");
        dom[18] = new Dominos("ELECTRONIC CITY", "SHOP NO. 6-7, 1st MAIN, 2nd CROSS, SURVEY NO. 14, HOSUR ROAD, ELECTRONIC CITY, PHASE-1, BANGALORE", "08028527360");
        dom[19] = new Dominos("Rajarajeshwari Nagar", "COUNTER 13 & 14, WORLD CAF?, 3rd FLOORGOPALAN LEGACY, NO 148, MYSORE ROAD, DIVISION NO 33, BANGALORE", "08026753393");
        dom[20] = new Dominos("Kalyanagar", "#G1, 39/2, , HENNUR MAIN ROAD, KALYAN NAGAR, BANGALORE", "08022300500");
        dom[21] = new Dominos("Hoysalanagar", "GF, 72&73, HOYSALA NAGAR, T C PALYA MAIN ROAD, HORAMAVU VILLAGE, BANGALORE", "08065467758");
        dom[22] = new Dominos("HRBR LAYOUT", "NO 5, M - 403, 2ND BLOCK, KALYAN NAGAR, HRBR LAYOUT, BANGALORE", "08025429071");
        dom[23] = new Dominos("HSR LAYOUT", "GROUND FLOOR, # 1573, SECTOR 1(AGARA), HSR LAYOUT, BANGALORE", "08025727781");
        dom[24] = new Dominos("INDIRANAGAR", "NO. 303, 100 FT ROAD, INDIRA NAGAR, 2ND STAGE, BANGALORE", "08025217444");
        dom[25] = new Dominos("JP NAGAR", "GROUND FLOOR, NO. 7, OPP.SHEKAR NETHRALAYA,100 FT. ROAD, , BANGALORE", "08026590265");
        dom[26] = new Dominos("JAYANAGAR", "1577, 11TH MAIN, NEAR POST OFFICE, 4 TH BLOCK, JAYA NAGAR, BANGALORE", "08026659696");
        dom[27] = new Dominos("kORAMANGaLA", "PLOT NO-897, 6TH BLOCK, 80 FT PERIPHERAL ROAD, NEAR INDOOR STADIUM, KORAMANGLA, BANGALORE", "08025528888");
        dom[28] = new Dominos("MALLESWARAM", "NO. 22, 5th CROSS, NEAR MALLESHWARAM CIRCLE, MALLESHWARAM, BANGALORE", "08023343330");
        dom[29] = new Dominos("BEL ROAD", "GROUND FLOOR, OLD NO.14/529, NEW NO. 14, NEW BEL ROAD CHICKKAMARANAHALLI, BANGALORE", "08023512731");
        dom[30] = new Dominos("Marathalli", "71, MAHADEVPURA, MARATHALLI- KR PURAM, Nr. MARATHALLI BRIDGE PROPERTY NO 27, HOBLI, OUTER RING ROAD, BANGALORE", "08025237122");
        dom[31] = new Dominos("RICHMOND TOWN", "SHOP NO. 2, NO. 13, GROUND FLOOR, REHINUS STREET, RICHMOND TOWN, NEXT TO TV9 OFFICE OPPOSITE TO HOCKEY STADIUM, BANGALORE", "08022484848");
        dom[32] = new Dominos("SARJAPUR", "No. 18/2A, ANMBALIPURA VILLAGE, VARTHUR HOBLI, BELLANDUR GATE, OPP. IDEB SPRING FIELDS, SARJAPUR MAIN ROAD, BANGALORE", "08028440901");
        dom[33] = new Dominos("RAJAJINAGAR", "GROUND FLOOR, GOLDEN HEIGHT, RAMKUMAR MILLS, DR. RAJKUMAR ROAD, # 1/2, C 59TH DROSS, 4TH M BLOCK, RAJAJI NAGAR, BANGALORE");
        dom[34] = new Dominos("VIDYANARAYANAPURA", "573, 2nd BLOCK, HMT LAYOUT, VIDYANARAYANA PURA, BANGALORE", "08023649980");
        dom[35] = new Dominos("WHITEFIELD", "GROUND FLOOR, 795/691, WHITEFIELD SUB-DIVISION, WHITEFIELD ROAD, NEAR SHELL PETROL PUMP,, RAMAGONDANAHALLI, BANGALORE", "08028476900");
        dom[36] = new Dominos("KANAKPURA", "SITE NO 4, SURVEY NO 40/1, 2 & 5, NEXT TO METRO, YELACHENAHALLI KANAKPURA ROAD, INDUSTRIAL LAYOUT, 1ST STAGE, UTTARAHALLI, HOBLI, BANGALORE", "08065838368");
        dom[37] = new Dominos("YELAHANKA", "GROUND FLOOR, PLOT NO 60, DIAMOND ARCADE, 60FT ROAD, F BLOCK, SHAKARA NAGAR, BANGALORE", "08023627771");
        dom[38] = new Dominos("YELAHANKA SATELLITE TOWN", "719, MIG SECTOR 1, DODDABALLAPUR MAIN ROAD, YELAHANKA SATEELITE TOWN, BANGALORE", "08028564910");

        kfc[0] = new Kfc("Indiranagar", "Chinmaya Mission Hospital Rd, Indira Nagar, Bengaluru, Karnataka", "08025260999");
        kfc[1] = new Kfc("Gandhi Nagar", "Danvanthri Road, Gandhi Nagar, Bengaluru", "08041494959");
        kfc[2] = new Kfc("MG Road", "MG Road, Bengalooru, Karnataka", "08025550323");
        kfc[3] = new Kfc("Koramangala", "21, Landmark @ The Forum, The Forum, Hosur Rd, Koramangala, Bengaluru, Karnataka 560034", "08022067676");
        kfc[4] = new Kfc("Jayanagar", "11 Main Road, Jayanagar, Bengaluru, Karnataka", "08041737396");
        kfc[5] = new Kfc("Jayanagar", "4th Block, Near Bhs College, 11th Main, 10th Cross, Jaya Nagar, Jaya Nagar, Bengaluru, Karnataka 560011", "08042198198");
        kfc[6] = new Kfc("Frazer Town", "Mosque Rd, Frazer Town, Bengaluru, Karnataka");
        kfc[7] = new Kfc("Airport Road", "Airport road, Old Airport Road, Ramagiri, Murugeshpalya, Bengaluru, Karnataka");
        kfc[8] = new Kfc("Tilak Nagar", "11th Main Road, Tilak Nagar, Bengaluru, Karnataka");
        kfc[9] = new Kfc("Basaweshwar Nagar", "Basaveshwara Nagar, Bengaluru, Karnataka 560079");
        kfc[10] = new Kfc("Cunningham Road", "14, Cunningham Road, Vasanth Nagar, Bengaluru, Karnataka- 560001", "08041231393");
        kfc[11] = new Kfc("Rajarajeshwari Nagar", "Gopalan Archade, Rajarajeshwari Nagar, Bengaluru, Karnataka");
        kfc[12] = new Kfc("Commercial Street", "Commercial St, Bengaluru, Karnataka-560001", "08041510390");
        kfc[13] = new Kfc("Whitefield", "ITPL, Whitefield, Bangalore-560066");
        kfc[14] = new Kfc("Koramangala", "Shop No :13,16 & 17, Egipura village, Intermediate Ring Road, Koramangala, Bangalore-560095");
        kfc[15] = new Kfc("BEL Road", "Plot#117, Shop#1, Ground & First Floor, AEGS Layout, New BEL Road, Bangalore-560054", "08042111515");
        kfc[16] = new Kfc("Malleswaram", "Upper Ground Floor, Mantri Mall,Sampige Road, Malleshwaram-560010");
        kfc[17] = new Kfc("Kalyanagar", "4M-403, Ground Floor, 80 Ft Kammanahalli Main Road, HRBR 3rd Block, Kalyan Nagar, Bangalore-560043");
        kfc[18] = new Kfc("RT Nagar", "#177,RT Nagar Main Road, Matadahalli Opposite BATA Showroom, Bangalore -560034");
        kfc[19] = new Kfc("Basavanagudi", "79/1, Aishwarya Sampurna Vani Vilas Road, Basavangudi, Bangalore-560004");
        kfc[20] = new Kfc("Banashankari", "80 Ft Road, 6th Block, 3rd Stage, Katriguppe Main Road, next to Big Bazaar, Banshankari, Bangalore-560085");
        kfc[21] = new Kfc("West of Chord Road", "Site # 729, Modi Hospital Road,2nd Stage, West of Chord Road, Bangalore-560008");

        mcD[0] = new McD("Adugodi", "Shop No. 1, Ground Floor, The Forum, NH 7, Adugodi,Bangalore-560095", "08066000666");
        mcD[1] = new McD("Bagmane Tech Park", "The Escape Mall,Food court,Ground Floor,Byrasandra Village,Bagmane Tech Park,Bangalore-560093", "08066000666");
        mcD[2] = new McD("Basavanagudi", "Bull Temple Road, Basavanagudi, Bangalore", "9620206960");
        mcD[3] = new McD("Basaweshwar Nagar", "1st Cross Rd, Basaweshwar Nagar, Bangalore", "08066000666");
        mcD[4] = new McD("Bennigana Halli", "Municipal No. 3 Old Madras Road Sy No 10 of Benniganahalli Village Krishnapuram Hobli, Bennigana Halli, Bangalore-560001", "9620206954");
        mcD[5] = new McD("Brigade Road", "Shop #43, 44 & 45, Brigade Road, Bangalore G.P.O., Bangalore - 560001", "08066000666");
        mcD[6] = new McD("Commercial Street", "#132 , Commercial Street, Opp Commercial Street Police Station, Bangalore- 560001", "08066000666");
        mcD[7] = new McD("Frazer Town", "Mosque Rd, Frazer Town Bangalore", "08066000666");
        mcD[8] = new McD("HSR Layout", "No. 1, Near BDA Complex, 14th Main Road, HSR Layout,Bangalore-560102", "08066000666");
        mcD[9] = new McD("Indiranagar", "Chinmaya Mission Hospital Rd, Indira Nagar,Bangalore", "9620206956");
        mcD[10] = new McD("Kasturba Road", "#10,Kasturba Road, Bangalore-560001", "9620206957");
        mcD[11] = new McD("Koramangala", "The Forum Mall, Shop #21, Ground Floor, Hosur Road, Koramangala, Bangalore – 560095", "08066000666");
        mcD[12] = new McD("Madivala", "Ground Floor, Total Mall, Hosur Road, Koramangala 2B Block, Madivala,Bangalore-560034", "08022067733");
        mcD[13] = new McD("Mathikere", "Ground Floor, Shashi Arcade, 215, New BEL Rd, AG's Officers Layout, Mathikere, Bangalore-560054", "08066000666");
        mcD[14] = new McD("Murugeshpalya", "Cauvery Nagar, Murugeshpalya,Bengaluru-560034", "08025275183");
        mcD[15] = new McD("Rajarajeshwari Nagar", "Gopalan Mall, Unit No. 33&33/A, # 148, Mysore Road, Bengaluru, Karnataka 560026", "9620206952");
        mcD[16] = new McD("Bel Road", "Old Nilgiris Building, Sanjay Nagar, New Bel Road, R.M.V. Extn. 2nd St., Bangalore - 560094", "08066000666");
        mcD[17] = new McD("Trinity Circle", "Lido Mall,1/4,Ground Floor,Swami Vivekananda Road,Trinity Circle,Bangalore-560008", "9620219509");
        mcD[18] = new McD("Varthur", "RMZ Ecospace, Ground Floor, Unit No. 001, Bellandur, Varthur Hobli Road, Varthur, Bangalore - 560087", "9620206953");
        mcD[19] = new McD("Cunningham Road", "#15,16 & 17, Sigma Mall, Cunningham Road, Vasantha Nagar, Bangalore - 560052", "08066000666");

        PH[0] = new PizzaHut("Airport Road", "124, Airport Road,Murugesh Palya, Bangalore", "08025223637");
        PH[1] = new PizzaHut("Banashankari", "2469/2470, 24th Cross, 21st Main road, Banashankari 2nd stage Cross, Opposite BDA Complex, Bangalore", "08026715993");
        PH[2] = new PizzaHut("Banashankari III stage", "6th Block, 100 Ft Road, Opp Megamart, Bangalore", "08026799994");
        PH[3] = new PizzaHut("Basaweshwar Nagar", "87, Bhima Jyothi Colony, West of chord road, Bangalore", "08041287999");
        PH[4] = new PizzaHut("BEL Road", "Opp. Ramaiah Hospital, New BEL Road, Bangalore", "08023519701");
        PH[5] = new PizzaHut("Brigade Road", "54 Monarch Plaza, Brigade Road, Bangalore", "08041129994");
        PH[6] = new PizzaHut("CMH Road", "No. 11A, CMH Road, Indiranagar, Bangalore", "08025260502");
        PH[7] = new PizzaHut("Coles Road", "No. 66/1,Coles Road, Frazer Town, Bangalore", "08025512693");
        PH[8] = new PizzaHut("Domlur", "Amarjyoti Colony, Domlur Village, Koramangala Intermediate Ring Road, Bangalore", "08041538420");
        PH[9] = new PizzaHut("Hosur Main Road", "Shop 117/118/119 , Ist floor, Forum Mall, No 21, Hosur Main Road, Bangalore", "08022067994");
        PH[10] = new PizzaHut("ITPL", "G10 D, Tech Park Mall, International Technology Park Limited, Whitefield Main Road, Bangalore", "08028416280");
        PH[11] = new PizzaHut("Jayanagar", "Suraj Tower, 216/13, 27th Cross, Jayanagar, Bangalore", "08026532999");
        PH[12] = new PizzaHut("Kamanahalli", "Kamanahalli Main Road, HRBR Layout, Banaswadi Main, Bangalore", "08025435993");
        PH[13] = new PizzaHut("Kanakpura", "363 / 70 Jarganhalli, Kanakpura Main Road, Bangalore", "08026542998");
        PH[14] = new PizzaHut("Koramangala", "80 Ft Road, Koramangala 6th Block, Bangalore", "08025532469");
        PH[15] = new PizzaHut("Cunningham Road", "Prestige Centre Point, 7, Edwards Road, Bangalore", "08022282410");
        PH[16] = new PizzaHut("RT Nagar", "Ground Floor, Jalaram Colony, P&T Colony, RT Nagar (Ganeshalli), Bangalore", "08023331507");
        PH[17] = new PizzaHut("Sadashivnagar", "Gr. Floor, 32, Anjan Complex 2nd Main Road,Vyalikaval, Bangalore", "08023312752");
        PH[18] = new PizzaHut("Vijayanagar", "No. 16, 17th cross, 21st main, MC layout, Vijaya Nagar, Bangalore");
        PH[19] = new PizzaHut("Bannerghatta Road", "Plot no.9 Amalobdhava Tower, Opp IIM (B), Bangalore", "08060606022");
        PH[20] = new PizzaHut("Malleswaram", "3rd Floor, Mantri Square Mall, Sampige Road,Malleswaram, Bangalore", "08039883988");
        PH[21] = new PizzaHut("Banaswadi", "o.19/3,Banaswadi Dhakle, K.R.Puram Hobli, Bangalore", "08039883988");
        PH[22] = new PizzaHut("Marathalli", "321/30,Munnekolalu village, Mahadevpura CMC, Bangalore", "08039883988");
        PH[23] = new PizzaHut("BTM Layout", "Plot No-38, 1st Stage, 1st Phase, BTM Layout,Madivala Dollors Scheme, Bangalore", "08039883988");
        PH[24] = new PizzaHut("Electronic City", "v-3, 3rd cross Road, Bangalore", "08039883988");
        PH[25] = new PizzaHut("Rajarajeshwari Nagar", "287/B, 12th Cross Road, Ideal Home Township, Bangalore", "08064507777");
        PH[26] = new PizzaHut("HSR Layout", "Site no 2633, 27th main, 13th Cross,HSR Layout, 1st Sector, Bangalore", "08049072222");
        PH[27] = new PizzaHut("Bellandur", "Site no 203/205, 1st main,Varthur Hobli, Bellandur, Bangalore", "08049036666");

        PC[0] = new PizzaCorner("Victoria Road", "Central Central Square, Victoria Road, Bangalore", "08044112233");
        PC[1] = new PizzaCorner("Bannerghatta Road", "#A-1 First Floor, Above Fab Mall, Adjacent to HSBC, Bilekhally Village, Begur Hobli, Bannerghatta, Bangalore", "08044112233");
        PC[2] = new PizzaCorner("Brigade Road", "Deena Complex, 1st Floor, 185, Brigade Road, Bangalore", "08044112233");
        PC[3] = new PizzaCorner("Brookefield", "Ground Floor, Cosmos mall, Kundalahalli Road, Brookefield, Bangalore", "08044112233");
        PC[4] = new PizzaCorner("Cox Town", "79, Front Line Manor, Next to Citibank ATM, Wheelers Road, Coxtown, Bangalore", "08044112233");
        PC[5] = new PizzaCorner("Electronic City", "BPCL Petrol Station, Electronic City, Hosur Road, Bangalore", "08044112233");
        PC[6] = new PizzaCorner("Hebbal", "Esteem Mall, Bellary Road, Hebbal, Bangalore", "08044112233");
        PC[7] = new PizzaCorner("Koramangala", "11, Transit Food Court, Forum Mall, Koramangala, Bangalore", "08044112233");
        PC[8] = new PizzaCorner("Richmond Town", "Pitstop Food Court, Garuda Mall, Magrath Road, Richmond Town, Bangalore", "08044112233");
        PC[9] = new PizzaCorner("Whitefield", "GE John F welch Technology Centre, Plot No.122, Export Promotional Industrial Park, Hoodi Village, Whitefield, Bangalore", "08044112233");
        PC[10] = new PizzaCorner("Indiranagar", "No.54, MSK Plaza, 100 Feet Road, Defence Colony, Indiranagar 2nd Stage, Bangalore-560038", "08044112233");
        PC[11] = new PizzaCorner("Ramnagaram", "Food court, Kitchen 2, Innovative Film city, Bidadi, Ramnagaram Taluk, Bangalore", "08044112233");
        PC[12] = new PizzaCorner("Jayanagar", "5, 11th Main, 4th Block, Jayanagar, Bangalore-560011", "08044112233");
        PC[13] = new PizzaCorner("Kathriguppe", "RAC Towers, 80 Ft Road, Kathriguppe, Bangalore-560085", "08044112233");
        PC[14] = new PizzaCorner("MG Road", "86, Spencer Towers, M.G. Road, Bangalore", "08044112233");
        PC[15] = new PizzaCorner("Malleswaram", "504, 3rd Floor, 14th Cross, Sampige Road, Malleshwaram, Bangalore", "08044112233");
        PC[16] = new PizzaCorner("Old Madras Road", "Counter No.02, The Bay, RMZ Infinity, No.3, Old Madras Road, Bangalore", "08044112233");
        PC[17] = new PizzaCorner("RT Nagar", "14, 1st D Main Road, Ganganagar Extension, RT Nagar, Bangalore", "08044112233");
        PC[18] = new PizzaCorner("Salarpuria", "Polynation Food Court, Fourth Floor, Salarpuria Tech Point, 100 ft. road, (intermediate inner ring road), Bangalore", "08044112233");
        PC[19] = new PizzaCorner("Varthur", "Restaurant No.2, 4th Floor, Total Mall, Kaikondarahalli Village, Varthur, Hobli, Bangalore", "08044112233");
        PC[20] = new PizzaCorner("Electronic City", "EC-4, Wipro Technologies Keonics, Electronic City, Bangalore-560100", "08044112233");
        PC[21] = new PizzaCorner("Rajarajeshwari Nagar", "Gopalan Arcade, Shop #2A 3rd Floor, 447/18/2 Panthara Palya, Rajarajeshwari Nagar 1st Block, Mysore Road, Bangalore", "08044112233");
        PC[22] = new PizzaCorner("Malleswaram", "FCO 63rd Floor, Food Court Mantri Mall, #1 2nd Main, Beside LIC Building, Near Sampige Theatre, Malleswaram, Bangalore", "08044112233");
        PC[23] = new PizzaCorner("Peenya", "#266/A 6th Main Road, Peenya 2nd Stage, 4th Phase, Bangalore", "08044112233");
        PC[24] = new PizzaCorner("Sarjapur", "Ground Floor, Total Mall, Kaikondarahalli Village, Varthur, Hobli, Bangalore", "08044112233");
        PC[25] = new PizzaCorner("Wonder La", "Wonder la Amusement Park, Jadenahalli, Hejjala Post, Mysore Road, Bangalore", "08044112233");

        big[1] = new BigBazaar("Old Madras Road", "08040907313", "192, Salarpuria Nova, Nagavarapalya, Old Madras Road, Bangalore-16");
        big[2] = new BigBazaar("Koramangala", "08040927845", "22, Salarpuria Tower, Hosur Road, Koramangala, Bangalore-95");
        big[3] = new BigBazaar("Banashankari III stage", "08040933737", "92/9, 80 Feet Road, Banashankari 3rd Stage, Bangalore-85");
        big[4] = new BigBazaar("Byatarayanapura", "08066208600", "Mysore Road, Byatarayanapura, Bangalore-26");
        big[5] = new BigBazaar("KH Road", "08067125816", "BMTC Bus Terminal, Double Road, K.H. Road, Bangalore-27");
        big[6] = new BigBazaar("ITPL", "08041680412", "88, Sadar Mangala Village, ITPL, Bangalore-66");
        big[7] = new BigBazaar("JP Nagar", "08042198360", "45/1, Marena Halli, 9th Block, JP Nagar 2nd Phase, Bangalore-78");
        big[8] = new BigBazaar("BEL Road", "08023515851", "51, 80 Feet Road, New BEL Road, Bangalore-94");
        big[9] = new BigBazaar("Hebbal", "Maruti Arcade, Maruthi Nagar, nagashetty Halli");
        big[10] = new BigBazaar("Malleswaram", "0804099373", "Housuja's Mall, 166, Coconut Avenue Road, 5th Cross, Malleswaram, Bangalore-3");
        big[11] = new BigBazaar("Whitefield", "08041694523", "Cosmos Mall, 1st Floor, Whitefield Main Road, Bangalore-66");
        big[12] = new BigBazaar("Sanjaya Nagar", "08023515852", "51, 80 Feet Road, Sanjaya Nagar, Bangalore-94");

        shop[0] = new ShoppingMalls("Garuda", "Richmond Town", "08041124863", "15, Commissionaire Junction, Magrath Road,Richmond Town, Bangalore-25");
        shop[1] = new ShoppingMalls("Sri Garuda Swagath Mall", "Jayanagar 4th Block", "08026647181", "Tilak Nagar Main Road, Jayanagar IV T Block, Bangalore-41");
        shop[2] = new ShoppingMalls("Gopalan Legacy", "Mysore Road", "08026752122", "148, Mysore Road, Bangalore-26");
        shop[3] = new ShoppingMalls("Gopalan Arch", "Rajarajeshwari Nagar", "08028602945", "Mysore Road, Raja Rajeshwari Nagar, Bangalore-39");
        shop[4] = new ShoppingMalls("Gopalan Innovation", "Bannerghatta Road", "08065336764", "JP Nagar III Phase, Bannerghatta Road, Bangalore-76");
        shop[5] = new ShoppingMalls("Total", "Madivala", "08041559299", "Madivala Commercial Plaza, Junction of Hosur Road, Madiwala, Bangalore-68");
        shop[6] = new ShoppingMalls("Total", "Airport Road", "08025222501", "Murugesh Palya, HAL Airport Road, Bangalore-17");
        shop[7] = new ShoppingMalls("Total", "Sarjapura Road", "08040767800", "Kaikondana Halli, Sarjapura Road, Bangalore-35");
        shop[8] = new ShoppingMalls("Total", "Mahadevapura", "08049011900", "Mahadevapura Outer Ring Road, Mahadevapura, Bangalore-37");
        shop[9] = new ShoppingMalls("Total", "Mysore Road", "08026741399", "Legacy Gopalan Mall, 53, 1st Floor, 148, Mysore Road, Bangalore-26");
        shop[10] = new ShoppingMalls("The Forum", "Koramangala", "08022067676", "Hosur Main Road, Koramangala, Bangalore-95");
        shop[11] = new ShoppingMalls("The Forum Value Mall", "Whitefield", "08025043700", "62, Whitefiled Main Road, Whitefield, Bangalore-66");
        shop[12] = new ShoppingMalls("Bangalore Central", "Jayanagar 9th Block", "08043320600", "Mantri Junction, Jaya Nagar 9th Block, Bangalore-69");
        shop[13] = new ShoppingMalls("Bangalore Central", "JP Nagar", "08042075129", "45/1&2, 45th Cross, JP Nagar II Phase,JP Nagar, Bangalore-69");
        shop[14] = new ShoppingMalls("Bangalore Central", "Residency Road", "08066930000", "47/48, Victoria Embassy, Recidency Road, Bangalore-25");
        shop[15] = new ShoppingMalls("Bangalore Central", "Bellandur", "08049029400", "Soul Space Spirit, Sarjapura Outer Ring Road, Bellandur, Bangalore-103");
        shop[16] = new ShoppingMalls("Mantri", "Malleswaram", "08030160000", "1, Sampige Road, Malleswaram, Bangalore-3");
        shop[17] = new ShoppingMalls("Royal Meenakshi", "Bannerghatta Road", "08026487299", "63, 13th Cross, Hulimaavu, Bannerghatta Road, Bangalore-76");

        sp[0] = new Spar("Bannerghatta Road", "08041811600", "No.39/4 & 44, Between Diary Circle & Bannerghatta Flyover, Bannerghatta Road, Bangalore - 560076 ");
        sp[1] = new Spar("Koramangala", "08043456918", "No.30, 3RD Floor, Oasis Centre, Opp Sony World, 100 Feet Inner Ring Road, 4TH Block, Koramangala, Bangalore - 560095");
        sp[2] = new Spar("Malleswaram", "08039378500", "No.1, Lower Ground Floor, Mantri Square Mall, Sampige Road, Malleswaram, Bangalore - 560003");

        str[0] = new StarBazaar("Koramangala", "08025535222", "66/25, HM Vibha Tower, Koramangala, Near Forum Mall, Bangalore-560029");
        str[1] = new StarBazaar("Srirampuram", " Vatal Nagaraj Rd, Manjunatha Nagar, Srirampuram, Bangalore");
        str[2] = new StarBazaar("Rajajinagar", "08023508444", "Golden Heights Building, Rajajinagar, 4th Block, Bangalore-10");
        str[3] = new StarBazaar("Rajarajeshwari Nagar", "08028606700", "18/2, Raja Rajeshwari Nagar, Mysore Road, Bangalore-98");


        category.append("Eatable", null);
        category.append("Shopping", null);

        rate.append("I'm Happy..Yayy !!", null);
        rate.append("Ummm..Satisfactory", null);
        rate.append("Phew ! Never Go Here..", null);
        top_five_place.addCommand(back);

        /*        d.setCurrent(can1);
        try
        {
        Thread.sleep(2000);
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
         */
        d.setCurrent(canv1);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* d.setCurrent(canvppl);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        d.setCurrent(Main);
        Main.setCommandListener(this);

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }

    public void commandAction(Command c, Displayable di) {
        if (c == Cre && di == Main) {
            d.setCurrent(Credits);
            Credits.setCommandListener(this);
        } else if (c == abt && di == Main) {
            d.setCurrent(About);
            About.setCommandListener(this);
        } else if (c == help && di == Main) {
            d.setCurrent(Help);
            Help.setCommandListener(this);
        } else if (c == back && (di == About || di == Credits || di == Help)) {
            d.setCurrent(Main);
            Main.setCommandListener(this);
        }
        else if(c==backtosearch && di==place)
        {

            Name.setString("");
                    area.setString("");
                     eatchoice.deleteAll();
            choice.deleteAll();
            d.setCurrent(fifth);
            fifth.setCommandListener(this);
        }
        else if(c==backtotopfive && di==place)
        {
            d.setCurrent(top_five_place);
            top_five_place.setCommandListener(this);
        }
        else if (c == signin && di == login) {
            hangout.deleteAll();
            hangout_choice.deleteAll();
            String pass=pwd.getString();
            String en=pwdEncrypt(pass);
      //      String fullname = Http("http://nomads.alwaysdata.net/loginvalid.php?" + "uname=" + username.getString() + "&" + "pwd=" + pwd.getString());
                  String fullname = Http("http://nomads.alwaysdata.net/loginvalid.php?" + "uname=" + username.getString() + "&" + "pwd=" + en);
  //          System.out.println(fullname);
            if (fullname.equals("not found")) {
                exist.setTimeout(Alert.FOREVER);
                username.setString("");
            pwd.setString("");
                d.setCurrent(exist, login);
                login.setCommandListener(this);

            } else {

                String hangout_id = Http("http://nomads.alwaysdata.net/displayhangouts.php?" + "uname=" + username.getString());
  //              System.out.println("1st print" + hangout_id);
                search_but = new StringItem("", "Search For More", StringItem.BUTTON);
                search_but.setDefaultCommand(ok);
                search_but.setItemCommandListener((ItemCommandListener) this);
                hangout.append("Nomad Name : " + fullname);
                hangout.append("\n");
                hangout.append(search_but);
          
                if (hangout_id.startsWith("f")) {
                    hangout.append("No Hangouts Added Yet");
                    hangout.addCommand(signout);
                    d.setCurrent(hangout);
                    hangout.setCommandListener(this);
                }//fullname request is sent and also retrieved in previous else if.
                else {
                    h = parseHangout(hangout_id);
                          h[0].hangout_place = replaceAll(h[0].hangout_place, "~", "  ");
                h[1].hangout_place = replaceAll(h[1].hangout_place, "~", " ");
                h[2].hangout_place = replaceAll(h[2].hangout_place, "~", " ");
                h[3].hangout_place = replaceAll(h[3].hangout_place, "~", " ");
                h[4].hangout_place = replaceAll(h[4].hangout_place, "~", " ");
                h[0].hangout_area = replaceAll(h[0].hangout_area, "~", " ");
                h[1].hangout_area = replaceAll(h[1].hangout_area, "~", " ");
                h[2].hangout_area = replaceAll(h[2].hangout_area, "~", " ");
                h[3].hangout_area = replaceAll(h[3].hangout_area, "~", " ");
                h[4].hangout_area = replaceAll(h[4].hangout_area, "~", " ");
                   // System.out.println("2nd Print" + hangout_id);

                    hangout.append(hangout_choice);
                    hangout_choice.append("--Your Hangouts--", null);
                    hangout_choice.append(h[0].hangout_place + ", " + h[0].hangout_area, null);
                    hangout_choice.append(h[1].hangout_place + ", " + h[1].hangout_area, null);
                    hangout_choice.append(h[2].hangout_place + ", " + h[2].hangout_area, null);
                    hangout_choice.append(h[3].hangout_place + ", " + h[3].hangout_area, null);
                    hangout_choice.append(h[4].hangout_place + ", " + h[4].hangout_area, null);

                    hangout.addCommand(done);
                    hangout.addCommand(signout);
                    d.setCurrent(hangout);
                    hangout.setCommandListener(this);
                }
            }
        } else if (c == done && di == hangout) {

            int num = hangout_choice.getSelectedIndex();

            switch (num) {
                case 1:
                    FoundPlace f1 = AddressExtract(h[0].id);
                    DisplayFuncFifth(f1);
                    break;
                case 2:
                    FoundPlace f2 = AddressExtract(h[1].id);
                    DisplayFuncFifth(f2);
                    break;
                case 3:
                    FoundPlace f3 = AddressExtract(h[2].id);
                    DisplayFuncFifth(f3);
                    break;
                case 4:
                    FoundPlace f4 = AddressExtract(h[3].id);
                    DisplayFuncFifth(f4);
                    break;
                case 5:
                    FoundPlace f5 = AddressExtract(h[4].id);
                    DisplayFuncFifth(f5);
                    break;
                default:SelectHang.setTimeout(Alert.FOREVER);
                        d.setCurrent(SelectHang, hangout);
                        hangout.setCommandListener(this);

            }
        } else if (c == register && di == login) {
            d.setCurrent(registration);
            registration.setCommandListener(this);
        } else if (c == back && di == registration) {
            d.setCurrent(login);
            login.setCommandListener(this);
        } else if (c == create && di == registration) {
            if (first_name.getString().length() > 0 && lastname.getString().length() > 0 && username_r.getString().length() > 0 && pwd_r.getString().length() > 6 && con_pwd_r.getString().length() > 6) {
                if (pwd_r.getString().equals(con_pwd_r.getString())) {
                     String pass=pwd_r.getString();
            String en=pwdEncrypt(pass);
                  //  String flags = Http("http://nomads.alwaysdata.net/registering.php?" + "fname=" + first_name.getString() + "&" + "lname=" + lastname.getString() + "&" + "em=" + username_r.getString() + "&" + "pd=" + pwd_r.getString());
              String flags = Http("http://nomads.alwaysdata.net/registering.php?" + "fname=" + first_name.getString() + "&" + "lname=" + lastname.getString() + "&" + "em=" + username_r.getString() + "&" + "pd=" + en);
                    int flag = Integer.parseInt(flags);
                    if (flag == 1) {
                        success.setTimeout(Alert.FOREVER);
                        d.setCurrent(success, login);
                        login.setCommandListener(this);
                    } else {
                        failure.setTimeout(Alert.FOREVER);
                        d.setCurrent(failure, registration);
                        username.setString("");
                        pwd_r.setString("");
                        con_pwd_r.setString("");
                        registration.setCommandListener(this);
                    }
                } else {
                    //Alert
                    //pwd_r.setString("");
                    //con_pwd_r.setString("");
                    nomatch.setTimeout(Alert.FOREVER);
                        d.setCurrent(nomatch, registration);
                        registration.setCommandListener(this);
                }
            } else {
                first_name.setString("");
                lastname.setString("");
                pwd_r.setString("");
                con_pwd_r.setString("");
                reg_alert.setTimeout(Alert.FOREVER);
                d.setCurrent(reg_alert, registration);

                registration.setCommandListener(this);
            }
        } else if (c == search && di == hangout) {
            Name.setString("");
            area.setString("");
            d.setCurrent(fifth);
            fifth.setCommandListener(this);
            fifth.setItemStateListener(this);
        } else if (c == back && di == fifth) {

            d.setCurrent(hangout);
            place.setCommandListener(this);
        } else if (c == search && di == fifth) {
            
            String Name_extract = new String();
            Name_extract = Name.getString();
            String Area_extract = new String();
            Area_extract = area.getString();
       //******************************************** If name is given and area is not !!
            if(Name_extract.length()>5 && Area_extract.length()<4)
            {
                System.out.println("Enter1");
String areac=NameGenerate(Name_extract);
top_five_place.deleteAll();
top_place_choice.deleteAll();
int num=category.getSelectedIndex();
String top_five=new String();
if(num==0){                top_five = Http("http://nomads.alwaysdata.net/ename.php?u="+areac)+"%";}
else {         top_five = Http("http://nomads.alwaysdata.net/sname.php?u="+areac)+"%";}
                System.out.println(top_five);
                p=parseAreas(top_five);
                p[0].Foundarea = replaceAll(p[0].Foundarea, "~", " ");
               p[1].Foundarea  = replaceAll(p[1].Foundarea, "~", " ");
                p[2].Foundarea= replaceAll(p[2].Foundarea, "~", " ");
                p[3].Foundarea= replaceAll(p[3].Foundarea, "~", " ");
                p[4].Foundarea = replaceAll(p[4].Foundarea, "~", " ");
                   // System.out.println("2nd Print" + hangout_id);

                    top_five_place.append(top_place_choice);
                    top_place_choice.append("--"+Name_extract+"--", null);
                    top_place_choice.append(p[0].Foundarea, null);
                    top_place_choice.append(p[1].Foundarea, null);
                    top_place_choice.append(p[2].Foundarea, null);
                    top_place_choice.append(p[3].Foundarea, null);
                    top_place_choice.append(p[4].Foundarea, null);
                    top_five_place.addCommand(done);
                    //top_five_place.addCommand(back);
                    d.setCurrent(top_five_place);
                    top_five_place.setCommandListener(this);
//                t = parseHangout(top_five);
               // t[0].top_place = replaceAll(t[0].top_place, "~", "  ");
                //t[1].top_place = replaceAll(t[1].top_place, "~", " ");
                //t[2].top_place = replaceAll(t[2].top_place, "~", " ");
                //t[3].top_place = replaceAll(t[3].top_place, "~", " ");
                //t[4].top_place = replaceAll(t[4].top_place, "~", " ");
           /*     t[0].top_area = replaceAll(t[0].top_area, "~", " ");
                t[1].top_area = replaceAll(t[1].top_area, "~", " ");
                t[2].top_area = replaceAll(t[2].top_area, "~", " ");
                t[3].top_area = replaceAll(t[3].top_area, "~", " ");
                t[4].top_area = replaceAll(t[4].top_area, "~", " ");
                   // System.out.println("2nd Print" + hangout_id);


                top_five_area.append(top_area_choice);
                 top_area_choice.append("--"+Name_extract+"--", null);
                    top_area_choice.append(t[0].top_area, null);
                    top_area_choice.append(t[1].top_area, null);
                    top_area_choice.append(t[2].top_area, null);
                    top_area_choice.append(t[3].top_area, null);
                    top_area_choice.append(t[4].top_area, null);
                   top_five_area.addCommand(done);
                   top_five_area.addCommand(back);
                    d.setCurrent(top_five_area);
                    top_five_area.setCommandListener(this);

*/
            }

            else if(Name_extract.length()<4 && Area_extract.length()>6)
            {
System.out.println("Enter1");
String areac=IdGenerate(Area_extract);
System.out.println(areac);
top_five_place.deleteAll();
top_place_choice.deleteAll();
int num=category.getSelectedIndex();
String top_five=new String();
if(num==0)
{      top_five = Http("http://nomads.alwaysdata.net/earea.php?u="+areac)+"%";}
else {top_five = Http("http://nomads.alwaysdata.net/sarea.php?u="+areac)+"%";}
                System.out.println(top_five);
                p=parseAreas(top_five);
//String top_five = Http("http://nomads.alwaysdata.net/registering.php?" + "fname=" + first_name.getString() + "&" + "lname=" + lastname.getString() + "&" + "em=" + username_r.getString() + "&" + "pd=" + pwd_r.getString());                String
        //        p = parseHangout(top_five);
             //   p[0].top_place = replaceAll(p[0].top_place, "~", "  ");
              //  p[1].top_place = replaceAll(p[1].top_place, "~", " ");
               // p[2].top_place = replaceAll(p[2].top_place, "~", " ");
                //p[3].top_place = replaceAll(p[3].top_place, "~", " ");
           //     p[4].top_place = replaceAll(p[4].top_place, "~", " ");
                p[0].FoundName = replaceAll(p[0].FoundName, "~", " ");
               p[1].FoundName  = replaceAll(p[1].FoundName, "~", " ");
                p[2].FoundName= replaceAll(p[2].FoundName, "~", " ");
                p[3].FoundName= replaceAll(p[3].FoundName, "~", " ");
                p[4].FoundName = replaceAll(p[4].FoundName, "~", " ");
                   // System.out.println("2nd Print" + hangout_id);

                    top_five_place.append(top_place_choice);
                    top_place_choice.append("--"+Area_extract+"--", null);
                    top_place_choice.append(p[0].FoundName, null);
                    top_place_choice.append(p[1].FoundName, null);
                    top_place_choice.append(p[2].FoundName, null);
                    top_place_choice.append(p[3].FoundName, null);
                    top_place_choice.append(p[4].FoundName, null);
                    top_five_place.addCommand(done);
                    top_five_place.addCommand(back);
                    d.setCurrent(top_five_place);
                    top_five_place.setCommandListener(this);
            }
            else if (Name_extract.length() < 4 && Area_extract.length()<4) {
                System.out.println("Good");

                Name.setString("");
                    area.setString("");

                login_alert.setTimeout(Alert.FOREVER);
                d.setCurrent(login_alert, fifth);
                fifth.setCommandListener(this);
            } else {
                int choice_extract = category.getSelectedIndex();
                FoundPlace f;
                search_id = IdExtract(choice_extract, Name_extract, Area_extract);
                System.out.println(search_id);
                f = AddressExtract(search_id);


                //   String address = f.FoundAdd;

                //    System.out.println("Name:"+Name_extract+"Area:"+Area_extract+" "+search_id);
                // if(address ! = (null))
                if (f.FoundAdd != null) {
                    DisplayFuncFifth(f);
                } else {
                    Name.setString("");
                    area.setString("");
                    place_Alert.setTimeout(Alert.FOREVER);
                    d.setCurrent(place_Alert, fifth);
                    fifth.setCommandListener(this);
                }
            }
            
        }
        else if(c==back && (di==top_five_place||di==top_five_area))
        {
            Name.setString("");
                    area.setString("");
                     eatchoice.deleteAll();
            choice.deleteAll();
            d.setCurrent(fifth);
            fifth.setCommandListener(this);
        }
        /*else if(c==done && di==top_five_area)
        {
            int num = top_area_choice.getSelectedIndex();

            switch (num) {
                case 1:
                    FoundPlace f1 = AddressExtract(t[0].id);
                    DisplayFuncFifth(f1);
                    break;
                case 2:
                    FoundPlace f2 = AddressExtract(t[1].id);
                    DisplayFuncFifth(f2);
                    break;
                case 3:
                    FoundPlace f3 = AddressExtract(t[2].id);
                    DisplayFuncFifth(f3);
                    break;
                case 4:
                    FoundPlace f4 = AddressExtract(t[3].id);
                    DisplayFuncFifth(f4);
                    break;
                case 5:
                    FoundPlace f5 = AddressExtract(t[4].id);
                    DisplayFuncFifth(f5);
                    break;
                default:SelectHang.setTimeout(Alert.FOREVER);
                        d.setCurrent(SelectHang, hangout);
                        hangout.setCommandListener(this);

            }
        }*/
        else if(c==done && di==top_five_place)
        {
            int num = top_place_choice.getSelectedIndex();

            switch (num) {
                case 1:
                    FoundPlace f1 = AddressExtract(p[0].Foundid);
                    DisplayFuncFifth(f1);
                    break;
                case 2:
                    FoundPlace f2 = AddressExtract(p[1].Foundid);
                    DisplayFuncFifth(f2);
                    break;
                case 3:
                    FoundPlace f3 = AddressExtract(p[2].Foundid);
                    DisplayFuncFifth(f3);
                    break;
                case 4:
                    FoundPlace f4 = AddressExtract(p[3].Foundid);
                    DisplayFuncFifth(f4);
                    break;
                case 5:
                    FoundPlace f5 = AddressExtract(p[4].Foundid);
                    DisplayFuncFifth(f5);
                    break;
                default:SelectHang.setTimeout(Alert.FOREVER);
                        d.setCurrent(SelectHang, hangout);
                        hangout.setCommandListener(this);

            }
        }
        else if (c == back && di == place) {
            Name.setString("");
            area.setString("");
            eatchoice.deleteAll();
            choice.deleteAll();
            d.setCurrent(fifth);
            fifth.setCommandListener(this);

        } else if (c == rev && di == place) {
            review.deleteAll();
            comment.setString("");
           // rate.deleteAll();
            Rating final_rate = new Rating(search_id, 0, 0, 0);
            String comment1 = new String();
            String comment2 = new String();
            String comment3 = new String();
       //     System.out.println("Entering the review");
        //    System.out.println("ID of the hangout selected :" + universal.Foundid);
            if (universal.Foundid.startsWith("e")) {
                //System.out.println("Entering the if");
                String rating = Http("http://nomads.alwaysdata.net/displayeratings.php?u=" + universal.Foundid);
                final_rate = extRating(rating);
               // System.out.println("Did it print??");
                comment1 = Http("http://nomads.alwaysdata.net/displayecom1.php?u=" + universal.Foundid);
                comment2 = Http("http://nomads.alwaysdata.net/displayecom2.php?u=" + universal.Foundid);
                comment3 = Http("http://nomads.alwaysdata.net/displayecom3.php?u=" + universal.Foundid);

                //System.out.println("Did it printlfyffyfy??");
            } else {
                String rating = Http("http://nomads.alwaysdata.net/displaysratings.php?u=" + universal.Foundid);
                final_rate = extRating(rating);
                comment1 = Http("http://nomads.alwaysdata.net/displayscom1.php?u=" + universal.Foundid);
                comment2 = Http("http://nomads.alwaysdata.net/displayscom2.php?u=" + universal.Foundid);
                comment3 = Http("http://nomads.alwaysdata.net/displayscom3.php?u=" + universal.Foundid);
            }

            comment1 = replaceAll(comment1, "~", " ");
            comment2 = replaceAll(comment2, "~", " ");
            comment3 = replaceAll(comment3, "~", " ");

           // System.out.println("Did it print??" + comment1);
            review.append("\n" + "How many people say this?");
            review.append("\n" + "\u2022" + final_rate.happy +"\t I'm Happy..Yayy !!");
            review.append("\n" + "\u2022" + final_rate.satis +"\t Ummm..Satisfactory");
            review.append("\n" + "\u2022" + final_rate.sad+"\t Phew ! Never Go Here.." );
            review.append("\n" + "-- Comments --");
            review.append("\n" + "\u2022" +comment1);
            review.append("\n" + "\u2022" +comment2);
            review.append("\n" +"\u2022" + comment3);
            review.append("\n"+"--Your Rating--");
            review.append(rate);
            review.append("\n"+"--Your Comment--");
            review.append(comment);
            review.addCommand(back);
            review.addCommand(done);
            d.setCurrent(review);
            review.setCommandListener(this);
        } else if (c == backtoprofile && di == review) {

            d.setCurrent(hangout);
            hangout.setCommandListener(this);
        } else if (c == done && di == review) {

            int num = rate.getSelectedIndex();
           // System.out.println(comment.getString());
            String com_mod = comment.getString();
            com_mod = replaceAll(com_mod, " ", "~");
            if (universal.Foundid.startsWith("e")) {
                switch (num) {
                    case 0:
                        String happy = Http("http://nomads.alwaysdata.net/RateE.php?u=" + universal.Foundid);
                        break;
                    case 1:
                        String satis = Http("http://nomads.alwaysdata.net/Rateokaye.php?u=" + universal.Foundid);
                        break;
                    case 2:
                        String sad = Http("http://nomads.alwaysdata.net/Ratesade.php?u=" + universal.Foundid);
                        break;
                }



                String hello = Http("http://nomads.alwaysdata.net/insertecom.php?u=" + universal.Foundid + "&" + "c=" + com_mod);
           //     System.out.println(hello);
            } else {
                switch (num) {
                    case 0:
                        Http("http://nomads.alwaysdata.net/Rates.php?u=" + universal.Foundid);
                        break;
                    case 1:
                        Http("http://nomads.alwaysdata.net/Rateokays.php?u=" + universal.Foundid);
                        break;
                    case 2:
                        Http("http://nomads.alwaysdata.net/Ratesads.php?u=" + universal.Foundid);
                        break;
                }
                Http("http://nomads.alwaysdata.net/insertscom.php?u=" + universal.Foundid + "&" + "c=" + com_mod);
            }
            thanku.setTimeout(Alert.FOREVER);
            d.setCurrent(thanku, place);

        } else if (c == back && di == review) {

            d.setCurrent(place);
            place.setCommandListener(this);
        } else if (c == signout && di == hangout) {
            hangout.deleteAll();
            d.setCurrent(login);
            login.setCommandListener(this);
        } else if (c == done && di == review) {
            d.setCurrent(fifth);
            fifth.setCommandListener(this);
        }

    }

    void DisplayFuncFifth(FoundPlace f) {


        place.deleteAll();
        universal = new FoundPlace(f.FoundName, f.Foundid, f.Foundarea, f.FoundAdd, f.FoundPhno);
        AddToHangout = new StringItem("", "Add to Hangout", StringItem.BUTTON);
        AddToHangout.setDefaultCommand(ok);
        AddToHangout.setItemCommandListener((ItemCommandListener) this);
        place.append(AddToHangout);

        call = new StringItem("", "Call", StringItem.BUTTON);
        call.setDefaultCommand(selectCommand);
        call.setItemCommandListener((ItemCommandListener) this);

        place.append("\n" + "\u2022" + " Name :" + universal.FoundName);
        place.append("\n" + "\u2022" + "Area : " + universal.Foundarea);
        place.append("\n" + "\u2022" + "Address :" + universal.FoundAdd);
        place.append(call);
        place.append("\n" + "\u2022" + "Phone Number : " + universal.FoundPhno);
        place.addCommand(backtosearch);
        place.addCommand(backtotopfive);
        place.addCommand(rev);

        d.setCurrent(place);
        place.setCommandListener(this);
    }

    public Hangout[] parseHangout(String ps) {   //String is sent as hangout1#hangout2#hangout3#hangout4#hangout5%
        Hangout[] h = new Hangout[5];

        for (int i = 0; i < 5; i++) {
            h[i] = new Hangout();
        }
        for (int i = 0; i < 5; i++) {
            if (ps.startsWith("%")) {
                return h;
            }
            h[i].hangout_place = ps.substring(0, ps.indexOf('@'));
           // System.out.println("Hi1");
            //System.out.println(h[i].hangout_place);
            ps = ps.substring(ps.indexOf('@') + 1);
            h[i].hangout_area = ps.substring(0, ps.indexOf('@'));
            //System.out.println(h[i].hangout_area);
            ps = ps.substring(ps.indexOf('@') + 1);
            h[i].id = ps.substring(0, ps.indexOf('@'));
            ps = ps.substring(ps.indexOf('@') + 1);
        }
        return (h);
    }
    public String pwdEncrypt(String text)
    {
 System.out.println("Password :"+text);
      String Ref="1923786540ckjmonpqstuvrxydahgeilbfwzCKJMONPQSTUVRXYDAHGEILBFWZ" ;
       String Result="";
       String Char;
       int Num;
       String EncodeChar;
      for (int Count=0; Count<text.length(); Count++) {

           Char=text.substring(Count, Count+1);
           Num=Ref.indexOf(Char);
           EncodeChar=Ref.substring(Num+1, Num+2);
           Result += EncodeChar;
                   System.out.println("Password encrypted :"+Result+"encrypt");
 }
       System.out.println("Password encrypted :"+Result+"encrypt");
       return Result;
    }
 /*   public String pwdEncrypt(String str)
    {
         String s=null;
       StringBuffer sb = new StringBuffer(40);
        for ( int i = 0; i < str.length(); ++i )
 {
 char c = str.charAt(i);
 int j = (int) c;
       s = sb.append(j).toString();
 }
 System.out.println(s);
 double i=Double.parseDouble(s);
 String encrypt=decimalToHex(i);
 return encrypt;
    }
    public String decimalToHex(double i)
 {
 String hex1 = Double.toString(i);
    System.out.println("Hexa decimal: " + hex1);
    return hex1;
 }*/

        public FoundPlace[] parseAreas(String ps) {   //String is sent as hangout1#hangout2#hangout3#hangout4#hangout5%
        System.out.println(ps);
            FoundPlace[] h = new FoundPlace[5];

        for (int i = 0; i < 5; i++) {
            h[i] = new FoundPlace();
        }
        int i=0;
        while(!ps.equals("%"))
            //while(i!=5)
        {
            System.out.println(ps);
            h[i].Foundid=ps.substring(0, ps.indexOf('@'));
            h[i]=AddressExtract(h[i].Foundid);
            System.out.println("  Place: "+ h[i].FoundName+ " Area : "+h[i].Foundarea);
            ps = ps.substring(ps.indexOf('@') + 1);
            i++;

        }
            return h;
        }
          /*for (int i = 0; i < 5; i++) {
            if (ps==null) {
                return h;
            }*/
//System.out.println("Entered Parse Areas "+ps.substring(0, ps.indexOf('@')));
       /* for(int i=0;i<4;i++)
        {
            h[i].Foundid = ps.substring(0, ps.indexOf('@'));
            h[i]=AddressExtract(h[i].Foundid);
            ps = ps.substring(ps.indexOf('@') + 1);
                System.out.println(ps);
          System.out.println("  Place: "+ h[i].FoundName+ " Area : "+h[i].Foundarea);
        }*/

//        h[4].Foundid = ps.substring(0, ps.indexOf('@'));
  //          h[4]=AddressExtract(h[4].Foundid);

      

    
    

    public Rating extRating(String rs) {
        String rid, happy, sad, satis;

      //  System.out.println("Entering the funtion Extrating");

        happy = rs.substring(0, rs.indexOf('@'));
        int h = Integer.parseInt(happy);

        //System.out.println(Ratin.sad);
        rs = rs.substring(rs.indexOf('@') + 1);
        sad = rs.substring(0, rs.indexOf('@'));
        int s = Integer.parseInt(sad);

        //System.out.println(Ratin.satis);
        rs = rs.substring(rs.indexOf('@') + 1);
        satis = rs.substring(0, rs.indexOf('@'));
        rs = rs.substring(rs.indexOf('@') + 1);
        rid = rs.substring(0, rs.indexOf('@'));


        int st = Integer.parseInt(satis);
      //  System.out.println(rid + " " + h + " " + s + " " + st);
        Rating r = new Rating(rid, h, s, st);
        return r;
    }

    public FoundPlace AddressExtract(String id) {
        System.out.println("Entered "+id);
        System.out.println(id);
        System.out.println(" "+id.substring(1, 2));

        String address = new String();
System.out.println(id);
        //String e = "e";f
        //String s= "s";
        String ch = id.substring(1, 2);
        int num = Integer.parseInt(ch);
        // System.out.println("place : "+ ch);

        if (id.substring(0, 1).equals("e")) {
  //               System.out.println("e or s??  "+id.substring(0,1));
            switch (num) {
                case 0:
                    for (int i = 0; i < ccd.length; i++) {
                        if (id.equals(ccd[i].ccdid)) {
                            return (new FoundPlace("CafeCoffeeDay", id, ccd[i].ccdname, ccd[i].ccdadd, ccd[i].ccdno));
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < CH.length; i++) {
                        if (id.equals(CH[i].houid)) {
                            return (new FoundPlace("CornerHouse", id, CH[i].houname, CH[i].houadd, CH[i].houno));
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < dom.length; i++) {
                        if (id.equals(dom[i].domid)) {
                            return (new FoundPlace("Dominos", id, dom[i].domname, dom[i].domadd, dom[i].domno));
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < kfc.length; i++) {
                        if (id.equals(kfc[i].kfcid)) {
                            return (new FoundPlace("KFC", id, kfc[i].kfcname, kfc[i].kfcadd, kfc[i].kfcno));
                        }
                    }
                    break;
                case 4:
                    for (int i = 0; i < mcD.length; i++) {
                        if (id.equals(mcD[i].mcdid)) {
                            return (new FoundPlace("McDonalds", id, mcD[i].mcdname, mcD[i].mcdadd, mcD[i].mcdno));
                        }
                    }
                    break;
                case 5:
                    for (int i = 0; i < PC.length; i++) {
                        if (id.equals(PC[i].corid)) {
                            return (new FoundPlace("PizzaCorner", id, PC[i].corname, PC[i].coradd, PC[i].corno));
                        }
                    }
                    break;
                case 6:
                    for (int i = 0; i < PH.length; i++) {
                        //System.out.println("came inside pizza hut");
                        //System.out.println("id from class"+ PH[i].hutid);
                        //System.out.println("id inside function :"+ id);
                        if (id.equals(PH[i].hutid)) {
                            //System.out.println("id from class"+ PH[i].hutid);
                            //System.out.println("id inside function :"+ id);
                            // System.out.println("id is proper");
                            return (new FoundPlace("PizzaHut", id, PH[i].hutname, PH[i].hutadd, PH[i].hutno));
                        }
                    }
                    break;
            }
        } else {
            switch (num) {
                case 0:
                    for (int i = 1; i < big.length; i++) {
                        if (id.equals(big[i].bigid)) {
                            return (new FoundPlace("BigBazaar", id, big[i].bigname, big[i].bigadd, big[i].bigno));
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < shop.length; i++) {
                        if (id.equals(shop[i].shopid)) {
                            return (new FoundPlace("ShoppingMalls", id, shop[i].shoparea, shop[i].shopadd, shop[i].shopno));
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < sp.length; i++) {
                        if (id.equals(sp[i].spid)) {
                            return (new FoundPlace("Spar", id, sp[i].sparea, sp[i].spadd, sp[i].spno));
                        }
                    }
                    break;
                case 3:
                    for (int i = 1; i < spn.length; i++) {
                        if (id.equals(spn[i].spnid)) {
                            return (new FoundPlace("Spencers", id, spn[i].spnarea, spn[i].spndd, spn[i].spnno));
                        }
                    }
                    break;
                case 4:
                    for (int i = 1; i < str.length; i++) {
                        if (id.equals(str[i].starid)) {
                            return (new FoundPlace("StarBazaars", id, str[i].starea, str[i].stadd, str[i].stno));

                        }
                    }
                    break;
            }
        }

        return (new FoundPlace(null, null));
    }

    public String IdExtract(int category, String name_ex, String area_ex) {
        String extract_id = new String();
        if (category == 0) {

            for (int i = 0; i < plac.length; i++) {

                if (name_ex.equals(plac[i].pla)) {
                    //  System.out.println(name_ex);
                    // System.out.println(i);
                    extract_id = "e" + i;
                    // System.out.println(extract_id);
                    break;
                }
            }
        } else if (category == 1) {
            for (int i = 0; i < bazz.length; i++) {
                if (name_ex.equals(bazz[i].baz)) {
                    extract_id = "s" + i;
                    break;
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (area_ex.equals(a[i].areain)) {
                extract_id = extract_id + i;
                break;
            }
        }
       // System.out.println(extract_id);
        return extract_id;
    }

    public boolean callNumber(String Number) {
        boolean b = false;
    //    System.out.println("Entering call..");
        try {
            //          pauseApp();
            //          notifyPaused();
            b = platformRequest("tel:" + Number);
            resumeRequest();
        } catch (Exception e) {
            return false;
        }
        return b;
    }

    public void commandAction(Command cmd, Item itm) {
        if (itm == call && cmd == selectCommand) {
           // System.out.println("Phone number : $"+ universal.FoundPhno);
            callNumber(universal.FoundPhno);

        }
        if (itm == skip && cmd == ok) {
            d.setCurrent(login);
            username.setString("");
            pwd.setString("");
            login.setCommandListener(this);
        }

        if (itm == AddToHangout && cmd == ok) {
            //create hangout object and append
            universal.FoundName = replaceAll(universal.FoundName, " ", "~");
            universal.Foundarea = replaceAll(universal.Foundarea, " ", "~");
            int flag = 1;

            for (int i = 0; i < 5; i++) {
                System.out.println(h[i].hangout_place+" "+universal.FoundName+" "+h[i].hangout_area+" "+universal.Foundarea);
                if (h[i].hangout_place.equals(universal.FoundName) && h[i].hangout_area.equals(universal.Foundarea)) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 0) {
            //    System.out.println("Entering flag=0");
                already.setTimeout(Alert.FOREVER);
                d.setCurrent(already, place);
                place.setCommandListener(this);

            } else {
            //    System.out.println("http://nomads.alwaysdata.net/addtohangout.php?em=" + username.getString() + "&" + "hang=" + universal.FoundName + "@" + universal.Foundarea + "@" + universal.Foundid);
                String su = Http("http://nomads.alwaysdata.net/addtohangout.php?em=" + username.getString() + "&" + "hang=" + universal.FoundName + "@" + universal.Foundarea + "@" + universal.Foundid + "@");

                int flg = Integer.parseInt(su);
                if (flg == 1) {
                    suc.setTimeout(Alert.FOREVER);
                    d.setCurrent(suc, place);
                    place.setCommandListener(this);
                }
            }
           // d.setCurrent(place);
           // place.setCommandListener(this);
        }
        if (itm == search_but && cmd == ok) {
            //create hangout object and append
              Name.setString("");
            area.setString("");
            d.setCurrent(fifth);

            fifth.setCommandListener(this);
            fifth.setItemStateListener(this);
        }
    }

    String Http(String url) {
        String str = "";
        HttpConnection connection = null;
        InputStream inputstream = null;
        try {

            connection = (HttpConnection) Connector.open(url);

            //HTTP Request
            connection.setRequestMethod(HttpConnection.GET);
            connection.setRequestProperty("Content-Type", "//text plain");
            connection.setRequestProperty("Connection", "close");
            // HTTP Response
            // System.out.println("Status Line Code: " + connection.getResponseCode());
            //System.out.println("Status Line Message: " + connection.getResponseMessage());
            if (connection.getResponseCode() == HttpConnection.HTTP_OK) {
                //System.out.println(connection.getHeaderField(0)+ " " + connection.getHeaderFieldKey(0));
                //System.out.println("Header Field Date: " + connection.getHeaderField("date"));

                inputstream = connection.openInputStream();
                int length = (int) connection.getLength();
                if (length != -1) {
                    byte incomingData[] = new byte[length];
                    inputstream.read(incomingData);
                    str = new String(incomingData);
                } else {
                    ByteArrayOutputStream bytestream =
                            new ByteArrayOutputStream();
                    int ch;
                    while ((ch = inputstream.read()) != -1) {
                        bytestream.write(ch);
                    }
                    str = new String(bytestream.toByteArray());
                    bytestream.close();
                }
                // System.out.println(str);
            }
        } catch (IOException error) {
            //System.out.println("Caught IOException: " + error.toString());
            Alert gone = new Alert("No Net Connection");
            gone.setTimeout(Alert.FOREVER);
            d.setCurrent(gone, login);
        } finally {
            //  System.out.println("error");
            if (inputstream != null) {
                //  System.out.println("inputstream not null");
                try {
                    inputstream.close();
                } catch (Exception error) {
                    /*log error*/
                    //  System.out.println("error");
                }
            }
            if (connection != null) { //System.out.println("connection not null");
                try {
                    connection.close();
                } catch (Exception error) {
                    /*log error*/
                    //   System.out.println("error");
                }

            }
        }

        return str;
    }

    public void itemStateChanged(Item item) {

      //  System.out.println("Entered item state cahnges");
        if (item == Name && category.getSelectedIndex() == 0) {
            //fifth.append(eatchoice);

            eatchoice.deleteAll();
            eatchoice.append("--Your Choice--", null);

            for (int i = 0; i < plac.length; i++) {
                // System.out.println(plac.length);
                {
                    //-------------------------

                    //------------

                    if (Name.getString().length() == 1) {
                        if (plac[i].pla.toUpperCase().startsWith(Name.getString().toUpperCase())) {
                            //  	System.out.println("Area starts");
                            eatchoice.append(plac[i].pla, null);
                        }
                    } else if (Name.getString().length() > 1) {
                        if (plac[i].pla.startsWith(Name.getString().substring(0, 1).toUpperCase() + Name.getString().substring(1, (Name.getString().length())))) {
                            eatchoice.append(plac[i].pla, null);
                        }
                    }
                }
            }
        } else if (item == eatchoice) {
            {
                Name.setString(eatchoice.getString(eatchoice.getSelectedIndex()));
            }

        } else if (item == Name && category.getSelectedIndex() == 1) {
            // fifth.append(eatchoice);
            eatchoice.deleteAll();
            eatchoice.append("--Your Choice--", null);

            for (int i = 0; i < bazz.length; i++) {
                //  System.out.println(bazz.length);
                {
                    //-------------------------

                    //------------

                    if (Name.getString().length() == 1) {
                        if (bazz[i].baz.startsWith(Name.getString().toUpperCase())) {
                            //        	System.out.println("Bazaar starts");
                            eatchoice.append(bazz[i].baz, null);
                        }
                    } else if (Name.getString().length() > 1) {
                        if (bazz[i].baz.startsWith(Name.getString().substring(0, 1).toUpperCase() + Name.getString().substring(1, (Name.getString().length())))) {
                            eatchoice.append(bazz[i].baz, null);
                        }
                    }
                }
            }
        } else if (item == eatchoice) {
            {
                Name.setString(eatchoice.getString(eatchoice.getSelectedIndex()));
            }

        } else if (item == area) {
            choice.deleteAll();
            choice.append("-----SELECT AREA-----", null);
            for (int i = 0; i < a.length; i++) {
                //   System.out.println(a.length);
                {
                    //-------------------------

                    //------------

                    if (area.getString().length() == 1) {
                        if (a[i].areain.startsWith(area.getString().toUpperCase())) {
                            //System.out.println("Area starts");
                            choice.append(a[i].areain, null);
                        }
                    } else if (area.getString().length() > 1) {
                        if (a[i].areain.startsWith(area.getString().substring(0, 1).toUpperCase() + area.getString().substring(1, (area.getString().length())))) {
                            choice.append(a[i].areain, null);
                        }
                    }
                }
            }
        } else if (item == choice) {
            {
                area.setString(choice.getString(choice.getSelectedIndex()));
            }

        }
    }
    //For Second Screen

    class canv extends Canvas {

        Image image;
        Font f3 = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_LARGE);

        public void paint(Graphics g) {

            g.setColor(195, 33, 72); // RGB component
            g.fillRect(0, 0, getWidth(), getHeight()); // the co-ordinates
            g.setColor(250, 235, 215);
            g.setFont(f3);
            /*try {
                image = Image.createImage("/logo2.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }*/
           //          g.drawImage(image, getWidth() / 2, getHeight() / 2, 0);
            g.drawString("   nom-ADS  1.0"+"\n\n"+"  The Peoples"+"\n\n"+"           App ", 35, (getHeight()/2)-35, 0);
        }
    }

/*    class canvppl extends Canvas {

        Image image;
        Font f3 = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_LARGE);

        public void paint(Graphics g) {

            g.setColor(195, 33, 72); // RGB component
            g.fillRect(0, 0, getWidth(), getHeight()); // the co-ordinates
            g.setColor(250, 235, 215);
            g.setFont(f3);
            try {
                image = Image.createImage("/theppl.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
            g.drawImage(image, getWidth() / 2, getHeight() / 2, 0);
        }
    }
*/
    public String IdGenerate(String areaname) {

        String id = new String();
        for (int i = 0; i < a.length; i++) {
            if (areaname.toUpperCase().equals(a[i].areain.toUpperCase())) {
                id = "" + i;
                break;
            }
        }
        return id;
    }


        public String NameGenerate(String name) {
System.out.println("Entered Name Generate");
        String id = new String();
       int num=category.getSelectedIndex();
       if(num==0)
       {
        for (int i = 0; i < plac.length; i++) {
            if (name.toUpperCase().equals(plac[i].pla.toUpperCase())) {
                id = "" + i;
                break;
            }
        }}
       else
       {
        for (int i = 0; i < bazz.length; i++) {
            if (name.toUpperCase().equals(bazz[i].baz.toUpperCase())) {
                id = "" + i;
                break;
            }

       }
System.out.println(id);
        
    }return id;
        }
    class PizzaHut {

        String hutid;
        String hutname;
        String hutadd;
        String hutno;
        int hutflags;

        public PizzaHut(String name, String add, String no) {

            hutname = name;
            hutadd = add;
            hutno = no;
          //  String areac=IdGenerate(name);
            hutid = "e" + "6" + IdGenerate(name);
        //  String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+hutid+"&n=6"+"&a="+areac);
  //          System.out.println(hutid);
        }

        public PizzaHut(String name, String add) {
            hutname = name;
            hutadd = add;
            hutno = "08039883988";
            //String areac=IdGenerate(name);
            hutid = "e" + "6" + IdGenerate(name);
          //  String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+hutid+"&n=6"+"&a="+areac);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }
    }

    class BigBazaar {

        String bigid;
        String bigname;
        String bigadd;
        String bigno;
        int bigflags;

        public BigBazaar(String name, String no, String add) {
            bigname = name;
            bigadd = add;
            bigno = no;
            bigid = "s" + "0" + IdGenerate(name);

        //    String areac=IdGenerate(name);
      //      String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid+"&n=0"+"&a="+areac);
        }

        public BigBazaar(String name, String add) {
            bigname = name;
            bigadd = add;
            bigno = "9222221947";
    //        String areac=IdGenerate(name);
            bigid = "s" + "0" + IdGenerate(name);
  //          String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid+"&n=0"+"&a="+areac);
            //String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
    //        String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
            //System.out.println(h);
        }
    }

    class PizzaCorner {

        String corid;
        String corname;
        String coradd;
        String corno;
        int corflags;

        public PizzaCorner(String name, String add, String no) {
            corname = name;
            coradd = add;
            corno = no;
//String areac=IdGenerate(name);
            corid = "e" + "5" + IdGenerate(name);
            //String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+corid+"&n=5"+"&a="+areac);
        }

        public PizzaCorner(String name, String add) {
            corname = name;
            coradd = add;
            corno = "0808888888";
            //String areac=IdGenerate(name);
            corid = "e" + "5" + IdGenerate(name);
            //String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+corid+"&n=5"+"&a="+areac);
  //          String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }
    }

    class Dominos {

        String domname;
        String domadd;
        String domno;
        int domflags;
        String domid;

        public Dominos(String name, String add, String no) {
            domname = name;
            //String areac=IdGenerate(name);
            domadd = add;
            domno = no;
            domid = "e" + "2" + IdGenerate(name);
            //String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+domid+"&n=2"+"&a="+areac);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }

        public Dominos(String name, String add) {
            domname = name;
            //String areac=IdGenerate(name);
            domadd = add;
            domno = "08068886888";
            domid = "e" + "2" + IdGenerate(name);
            //String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+domid+"&n=2"+"&a="+areac);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }
    }

    class Kfc {

        String kfcname;
        String kfcadd;
        String kfcno;
        int kfcflags;
        String kfcid;

        public Kfc(String name, String add, String no) {
            kfcname = name;
            kfcadd = add;
            //String areac=IdGenerate(name);
            kfcno = no;
            kfcid = "e" + "3" + IdGenerate(name);
            //String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+kfcid+"&n=3"+"&a="+areac);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }

        public Kfc(String name, String add) {
            kfcname = name;
            kfcadd = add;
            kfcno = "0808888888";
            kfcid = "e" + "3" + IdGenerate(name);
            //String areac=IdGenerate(name);
            //String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+kfcid+"&n=3"+"&a="+areac);
  //          String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }
    }

    class McD {

        String mcdname;
        String mcdadd;
        String mcdno;
        int mcdflags;
        String mcdid;

        public McD(String name, String add, String no) {
            mcdname = name;
            //String areac=IdGenerate(name);
            mcdadd = add;
            mcdno = no;
            mcdid = "e" + "4" + IdGenerate(name);
            //String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+mcdid+"&n=4"+"&a="+areac);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }

        public McD(String name, String add) {
            mcdname = name;
            mcdadd = add;
            //String areac=IdGenerate(name);
            mcdno = "0808888888";
            mcdid = "e" + "4" + IdGenerate(name);
          //  String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+mcdid+"&n=4"+"&a="+areac);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }
    }

    class Ccd {

        String ccdname;
        String ccdadd;
        String ccdno;
        int ccdflags = 0;
        String ccdid;

        public Ccd(String name, String add, String no) {
            ccdname = name;
            ccdadd = add;
            ccdno = no;
        //    String areac=IdGenerate(name);
            ccdid = "e" + "0" + IdGenerate(name);
      //      String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+ccdid+"&n=0"+"&a="+areac);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }

        public Ccd(String name, String add) {
            ccdname = name;
            ccdadd = add;
            ccdno = "0808888888";
    //        String areac=IdGenerate(name);
            ccdid = "e" + "0" + IdGenerate(name);
  //          String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+ccdid+"&n=0"+"&a="+areac);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }
    }

    class CornerHouse {

        String houname;
        String houadd;
        String houno;
        int houflags;
        String houid;

        public CornerHouse(String name, String add, String no) {
//String areac=IdGenerate(name);

            houname = name;
            houadd = add;
            houno = no;
            houid = "e" + "1" + IdGenerate(name);
              System.out.println(houid);
        //      String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+houid+"&n=1"+"&a="+areac);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+bigid);
        }

        public CornerHouse(String name, String add) {
           String areac=IdGenerate(name);
            houname = name;
            houadd = add;
            houno = "0808888888";

            houid = "e" + "1" + areac;
            System.out.println(houid);
    //        String h = Http("http://nomads.alwaysdata.net/webdataeatinsert.php?eucode="+houid+"&n=1"+"&a="+areac);
                     }

        }
    

    class ShoppingMalls {

        String shopname;
        String shoparea;
        String shopadd;
        String shopno;
        int shopflags;
        String shopid;

        public ShoppingMalls(String name, String area, String add, String no) {
            shopname = name;
            shoparea = area;
            shopadd = add;
            shopno = no;
           shopid = "s" + "1" + IdGenerate(area);

        }

        public ShoppingMalls(String name, String area, String add) {
            shopname = name;
            shoparea = area;
            shopadd = add;
            shopno = "0808888888";
          shopid = "s" + "1" + IdGenerate(area);

        }
    }

    class Spar {

        String sparea;
        String spadd;
        String spno;
        int spflags;
        String spid;

        public Spar(String area, String no, String add) {

            sparea = area;
  //          String areac=IdGenerate(area);
            spadd = add;
            spno = no;
            spid = "s" + "2" + IdGenerate(area);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+spid+"&n=2"+"&a="+areac);
        }

        public Spar(String area, String add) {
            sparea = area;
  //          String areac=IdGenerate(area);
            spadd = add;
            spno = "0808888888";
            spid = "s" + "2" + IdGenerate(area);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+spid+"&n=2"+"&a="+areac);
        }
    }

    class StarBazaar {

        String starea;
        String stadd;
        String stno;
        int stflags;
        String starid;

        public StarBazaar(String area, String no, String add) {
            starea = area;
  //          String areac=IdGenerate(area);
            stadd = add;
            stno = no;
            starid = "s" + "4" + IdGenerate(area);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+starid+"&n=4"+"&a="+areac);
        }

        public StarBazaar(String area, String add) {
            starea = area;
            stadd = add;
  //          String areac=IdGenerate(area);
            stno = "0808888888";
            starid = "s" + "4" + IdGenerate(area);
//String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+starid+"&n=4"+"&a="+areac);
        }
    }

    class Spencer {

        String spnarea;
        String spndd;
        String spnno;
        int spnflags = 0;
        String spnid;

        public Spencer(String area, String no, String add) {
            spnarea = area;
          //  String areac=IdGenerate(area);
            spndd = add;
            spnno = no;
            spnid = "s" + "3" + IdGenerate(area);
  //          System.out.println(spnid);
        //    String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+spnid+"&n=3"+"&a="+areac);
        }

        public Spencer(String area, String add) {
            spnarea = area;
            spndd = add;
           // String areac=IdGenerate(area);
            spnno = "0808888888";
            spnid = "s" + "3" + IdGenerate(area);
  //          System.out.println(spnid);
      //      String h = Http("http://nomads.alwaysdata.net/webdatashopinsert.php?sucode="+spnid+"&n=3"+"&a="+areac);
        }
    }

    class Area {

        String areain;

        Area(String S) {
            areain = S;
        }
    }

    class Categor {

        String caty;

        Categor(String S) {
            caty = S;
        }
    }

    class Places {

        String pla;

        Places(String S) {
            pla = S;
        }
    }

    class Bazaars {

        String baz;

        Bazaars(String S) {
            baz = S;
        }
    }

    public class FoundPlace {

        String Foundid = new String();
        String FoundName = new String();
        String Foundarea = new String();
        String FoundAdd = new String();
        String FoundPhno = new String();

        public FoundPlace() {
        }
        public FoundPlace(String Add, String Phno) {
            //   FoundName = Name;
            FoundAdd = Add;
            FoundPhno = Phno;
        }

        public FoundPlace(String name, String id, String area, String add, String phno) {
            Foundid = id;
            FoundName = name;
            Foundarea = area;
            FoundAdd = add;
            FoundPhno = phno;
        }
    }

    public class Hangout {

        String hangout_place;
        String hangout_area;
        String id;

        public Hangout() {
            hangout_place = "No Hangouts Added Yet";
            hangout_area = "";
        }

        public Hangout(String hang, String area, String i) {
            hangout_place = hang;
            hangout_area = area;
            id = i;

        }
    }

    public class Top_areas {

        String top_place="McDonalds";
        String top_area="Rajarajeshwari Nagar";
        String id="e446";

        public Top_areas() {
            top_place = "No Hangouts Added Yet";
            top_area = "";
        }

        public Top_areas(String name, String area, String i) {
            top_place = name;
            top_area = area;
            id = i;

        }
    }
    public class Top_places {

        String top_place;
        String top_area;
        String id;

        public Top_places() {
            top_place = "No Hangouts Added Yet";
            top_area = "";
        }

        public Top_places(String name, String area, String i) {
            top_place = name;
            top_area = area;
            id = i;

        }
    }

    class Rating {

        int happy;
        int sad;
        int satis;
        String rid;

        public Rating(String id, int hap, int saddie, int satisfactory) {
            happy = hap;
            rid = id;
            satis = satisfactory;
            sad = saddie;
        }
    }
}
