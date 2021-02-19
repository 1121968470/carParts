package com.carmold.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author huanghui
 * @version 0.9
 * @since 2018-5-30
 *
 */

public class TxtIO {
    public static void main(String[] args) {
    }


    public void Text() {
        TxtIO myFile = new TxtIO();
        try {
            myFile.creatTxtFile("m2");
            List<String[]> list = new ArrayList<String[]>();
//			myFile.writeTxtFile("显示的是追加的信息");
            System.out.println("请选择：1.打开文本 2.生成转换结果");
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            if ("1".equals(s)) {
                Runtime.getRuntime().exec("cmd.exe /c start "+myFile.filenameTemp);
            }
            if ("2".equals(s)) {
//				list.add(new String[]{"\\\\n\" \\+","","",""});
//				list.add(new String[]{"\"","","",""});

//				list.add(new String[]{"\\b\\w","UP","","","3"});
//				list.add(new String[]{"k","d","","","1"});
//				list.add(new String[]{"","","list.get(i).put(exportKey.split(\",\")[@@index@@], t.get","().toString());"});
//				list.add(new String[]{"get\\w+()","","","","2"});
                //首字母大写
//				list.add(new String[]{"\\b\\w","UP","","","3"});
                //java用sql转普通
//				list.add(new String[]{"\\\\n\" \\+","","",""});
//				list.add(new String[]{"\"","","",""});
                //实体变量转excel导出字段
//                list.add(new String[]{"private \\w+ ", "", "", ","});
//                list.add(new String[]{";", "", "\"", "\""});
                //实体注释转excel导出表头
//				list.add(new String[]{"public static final String ","","",""});
//				list.add(new String[]{"=.*;","","Comprehensivem.","+\",\"+"});
                //实体注释转json
//				list.add(new String[]{"public static final String ALIAS_","","",""});
//				list.add(new String[]{"=",": '',//","",""});
//				list.add(new String[]{";","","",""});
                //sql转java用
//				list.add(new String[]{"","","\"","\\n\"+"});
                //读取html文本为js拼接用
//				list.add(new String[]{"","","\'","\'+"});
                //读取js文本为html
//				list.add(new String[]{"\\\\","","",""});
//				list.add(new String[]{"\"\\+|\'\\+|^\"|^\'","","",""});
                myFile.parseText();
            }
            if ("3".equals(s)) {
                String line = "qyczrjjcf,";
                String pattern = "^\\w";

                // 创建 Pattern 对象
                Pattern r = Pattern.compile(pattern);

                // 现在创建 matcher 对象
                Matcher m = r.matcher(line);
                if (m.find()) {
                    System.out.println("Found value: " + m.group(0));
                } else {
                    System.out.println("NO MATCH");
                }
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义修改
     * new String[]{regex, replacement, start, end, mode}
     * mode:没有则普通模式
     * 1 截取 regex开始 replacement结尾 (start end)指定第几个
     * 2 保留 保留匹配的字符串 replacement为转换模式[ALL 全部][I 第start~end个][F 第一个][L 最后一个]
     * 3 转换 replacement为转换模式[UP 转大写][LOW 转小写]
     * 关键字 [@@index@@ 下标][@@line@@ 行号]
     */
    public void parseText() {
        try {
            FileReader read = new FileReader(filenameTemp);
            BufferedReader br = new BufferedReader(read);
            String row;
            while ((row = br.readLine()) != null) {
                String str = row;
                str = str.trim();
                if(!str.isEmpty()){
                    if (str.indexOf("private") != -1) {
                        str = str.replaceAll("private \\w+ ", "\"").trim();
                        str = str.replaceAll(";", "\"").trim()+",";
                        System.out.println(str);
                    } else {
                        if (str.indexOf("/**") == -1 && str.indexOf("*/") == -1) {
                            str = "\""+str.substring(str.indexOf("*")+1).trim() + "\":";
                            System.out.print(str);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String path = "D:/tool/";
    private static String filenameTemp;

    /**
     * 创建文件
     *
     * @throws IOException
     */
    public static boolean creatTxtFile(String name) throws IOException {
        filenameTemp = path + name + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            filename.createNewFile();
            return true;
        }
        return false;
    }

    /**
     * 写文件
     *
     * @param newStr 新内容
     * @throws IOException
     */
    public static boolean writeTxtFile(String newStr) throws IOException {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String filein = newStr + "\r\n";
        String temp = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis,codeString(filenameTemp));
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (;(temp = br.readLine()) != null;) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            throw e1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }

    /**
     * 判断文件的编码格式
     * @param fileName :file
     * @return 文件编码格式
     * @throws Exception
     */
    public static String codeString(String fileName) throws Exception{
        BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
//		System.out.println(p);
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }

        return code;
    }

    /**
     * 自定义修改
     * new String[]{regex, replacement, start, end, mode}
     * mode:没有则普通模式
     * 1 截取 regex开始 replacement结尾 (start end)指定第几个
     * 2 保留 保留匹配的字符串 replacement为转换模式[ALL 全部][I 第start~end个][F 第一个][L 最后一个]
     * 3 转换 replacement为转换模式[UP 转大写][LOW 转小写]
     * 关键字 [@@index@@ 下标][@@line@@ 行号]
     */
    public void customText(List<String[]> list) {
        try {
            FileReader read = new FileReader(filenameTemp);
            BufferedReader br = new BufferedReader(read);
            String row;
            int linecount = 0;
            while ((row = br.readLine()) != null) {
                String str = row;

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).length > 4) {
                        Pattern r = Pattern.compile(list.get(i)[0]);
                        Matcher m = r.matcher(str);
                        switch (Integer.parseInt(list.get(i)[4])) {
                            case 1:
                                str = str.substring(str.indexOf(list.get(i)[0]), str.lastIndexOf(list.get(i)[1]));
                                break;
                            case 2:
                                if ("F".equals(list.get(i)[1])) {
                                    if (m.find()) {
                                        str = m.group();
//									System.out.println("Found value: " + m.group(0));
                                    } else {
                                        System.out.println("NO MATCH");
                                    }
                                }
                                if ("L".equals(list.get(i)[1])) {
                                    while (true) {
                                        if (m.find() && !m.group().isEmpty()) {
                                            str = m.group();
//										System.out.println("Found value: " + m.group(0));
                                        } else {
                                            break;
                                        }
                                    }
                                }
                                if ("I".equals(list.get(i)[1])) {
                                    int s = Integer.parseInt(list.get(i)[2]);
                                    int e = Integer.parseInt(list.get(i)[3]);
                                    int x = 0;
                                    StringBuilder sb = new StringBuilder();
                                    while (true) {
                                        if (m.find() && !m.group().isEmpty()) {
                                            x++;
                                            if (s <= x && e >= x) {
                                                sb.append(m.group());
                                            }
//										System.out.println("Found value: " + m.group(0));
                                        } else {
                                            break;
                                        }
                                    }
                                    str = sb.toString();
                                }
                                if ("ALL".equals(list.get(i)[1])) {
                                    StringBuilder sb = new StringBuilder();
                                    while (true) {
                                        if (m.find() && !m.group().isEmpty()) {
                                            sb.append(m.group());
//										System.out.println("Found value: " + m.group(0));
                                        } else {
                                            break;
                                        }
                                    }
                                    str = sb.toString();
                                }
                                break;
                            case 3:
                                StringBuilder sb = new StringBuilder(str);
                                while (true) {
                                    if (m.find()) {
                                        if ("UP".equals(list.get(i)[1])) {
                                            sb.replace(m.start(), m.end(), m.group().toUpperCase());
                                        } else if ("LOW".equals(list.get(i)[1])) {
                                            sb.replace(m.start(), m.end(), m.group().toLowerCase());
                                        }
//									System.out.println("Found value: " + m.group(0));
                                    } else {
                                        break;
                                    }
                                }
                                sb.trimToSize();
                                str = sb.toString();
                                break;
                            default:
                                break;
                        }
                    } else {
                        str = list.get(i)[2] + str.replaceAll(list.get(i)[0], list.get(i)[1]).trim() + list.get(i)[3];
                    }
                }
                str = str.replaceAll("@@index@@", linecount + "");
                str = str.replaceAll("@@line@@", (linecount+1) + "");
                linecount++;
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取数据
     */
    public void readData1() {
        try {
            //FileReader read = new FileReader(filenameTemp);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filenameTemp),codeString(filenameTemp));
            BufferedReader br = new BufferedReader(isr);
            String row;
            while ((row = br.readLine()) != null) {
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readDate() {
        // 定义一个待返回的空字符串
        String strs = "";
        try {
            FileReader read = new FileReader(new File(filenameTemp));
            StringBuffer sb = new StringBuffer();
            char ch[] = new char[1024];
            int d = read.read(ch);
            while (d != -1) {
                String str = new String(ch, 0, d);
                sb.append(str);
                d = read.read(ch);
            }
            System.out.print(sb.toString());
            String a = sb.toString().replaceAll("@@@@@", ",");
            strs = a.substring(0, a.length() - 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strs;
    }

}
