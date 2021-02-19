package com.carmold.util;

import java.io.File;

//import com.jacob.activeX.ActiveXComponent;
//import com.jacob.com.ComThread;
//import com.jacob.com.Dispatch;
//import com.jacob.com.Variant;
//import org.jcp.xml.dsig.internal.dom.Utils;

public class PrintPDF {

//    public static boolean printOfficeFile(File f) {
//        if (f != null && f.exists()) {
//            String fileNameString = f.getName();
//            String postfixString = Utils.getPostfix(fileNameString);
//            if (postfixString.equalsIgnoreCase("xls") || postfixString.equalsIgnoreCase("xlsx")) {
//                /**
//                 * 功能:实现excel打印工作
//                 */
//                ComThread.InitSTA();
//                ActiveXComponent xl = new ActiveXComponent("Excel.Application");
//                try {
//                    // System.out.println("version=" +
//                    // xl.getProperty("Version"));
//                    // 不打开文档
//                    Dispatch.put(xl, "Visible", new Variant(false));
//                    Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
//                    // 打开文档
//                    Dispatch excel = Dispatch.call(workbooks, "Open", f.getAbsolutePath()).toDispatch();
//                    // 横向打印(2013/05/24)
//                    // Dispatch currentSheet = Dispatch.get(excel,
//                    // "ActiveSheet")
//                    // .toDispatch();
//                    // Dispatch pageSetup = Dispatch
//                    // .get(currentSheet, "PageSetup").toDispatch();
//                    // Dispatch.put(pageSetup, "Orientation", new Variant(2));
//                    // 每张表都横向打印2013-10-31
//                    Dispatch sheets = Dispatch.get((Dispatch) excel, "Sheets").toDispatch();
//                    // 获得几个sheet
//                    int count = Dispatch.get(sheets, "Count").getInt();
//                    // System.out.println(count);
//                    for (int j = 1; j <= count; j++) {
//                        Dispatch sheet = Dispatch
//                                .invoke(sheets, "Item", Dispatch.Get, new Object[] { new Integer(j) }, new int[1])
//                                .toDispatch();
//                        Dispatch pageSetup = Dispatch.get(sheet, "PageSetup").toDispatch();
//                        Dispatch.put(pageSetup, "Orientation", new Variant(2));
//                        Dispatch.call(sheet, "PrintOut");
//                    }
//                    // 开始打印
//                    if (excel != null) {
//                        // Dispatch.call(excel, "PrintOut");
//                        // 增加以下三行代码解决文件无法删除bug
//                        Dispatch.call(excel, "save");
//                        Dispatch.call(excel, "Close", new Variant(true));
//                        excel = null;
//                    }
//                    xl.invoke("Quit", new Variant[] {});
//                    xl = null;
//                    return true;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return false;
//                } finally {
//                    // 始终释放资源
//                    ComThread.Release();
//                }
//            } else if (postfixString.equalsIgnoreCase("doc") || postfixString.equalsIgnoreCase("docx")) {
//                ComThread.InitSTA();
//                ActiveXComponent wd = new ActiveXComponent("Word.Application");
//                try {
//                    // 不打开文档
//                    Dispatch.put(wd, "Visible", new Variant(false));
//                    Dispatch document = wd.getProperty("Documents").toDispatch();
//                    // 打开文档
//                    Dispatch doc = Dispatch
//                            .invoke(document, "Open", Dispatch.Method, new Object[] { f.getAbsolutePath() }, new int[1])
//                            .toDispatch();
//                    // 开始打印
//                    if (doc != null) {
//                        Dispatch.call(doc, "PrintOut");
//                        // 增加以下三行代码解决文件无法删除bug
//                        Dispatch.call(doc, "save");
//                        Dispatch.call(doc, "Close", new Variant(true));
//                        doc = null;
//                    }
//                    wd.invoke("Quit", new Variant[] {});
//                    wd = null;
//                    return true;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return false;
//                } finally {
//                    // 始终释放资源
//                    ComThread.Release();
//                }
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }
//    public static void main(String[] args) {
//        PrintDemo.printOfficeFile(new File("hehe.xls"));
//    }
}