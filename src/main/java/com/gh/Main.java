package com.gh;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static final String PLAYER_INVENTORY_SAVE_DATA_JSON_FILE_NAME = "\\PlayerInventorySaveData.json";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入PlayerInventorySaveData.json所在目录，" +
                "例如：C:\\Users\\xxx\\AppData\\LocalLow\\Endnight\\SonsOfTheForest\\Saves\\76561198109133310\\Multiplayer\\0524686653");
        String prefix = sc.nextLine();
        if (StrUtil.isBlank(prefix)) {
            prefix = "C:\\Users\\guohao\\AppData\\LocalLow\\Endnight\\SonsOfTheForest\\Saves\\76561198109133310\\Multiplayer\\0524686653";
        }
        String fileName = prefix + PLAYER_INVENTORY_SAVE_DATA_JSON_FILE_NAME;
        System.out.println("PlayerInventorySaveData.json的绝对路径为：" + fileName);
        JSONObject jsonObject = readJsonFile(fileName);


        generateZipLineObject(jsonObject);
    }

    /**
     * 生成高速功能相关的道具：钢线索，绳索，
     * @param jsonObject
     */
    private static void generateZipLineObject(JSONObject jsonObject) {

    }

    public static JSONObject readJsonFile(String filename){
        String jsonString = "";
        File jsonFile = new File(filename);
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((ch = reader.read()) != -1){
                stringBuffer.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonString = stringBuffer.toString();
        } catch (FileNotFoundException e){
            JSONObject notFoundJson = new JSONObject();
            notFoundJson.put("code","s");
            notFoundJson.put("msg","该地区GeoJson文件不存在！");
            return notFoundJson;
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONObject Data = jsonObject.getJSONObject("Data");
        String playerInventory = Data.getString("PlayerInventory");
        String replace = playerInventory.replace("//", "");
        JSONObject DataJSON = JSONObject.parseObject(replace);
        return DataJSON;
    }
}