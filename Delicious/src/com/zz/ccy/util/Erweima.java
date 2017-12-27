package com.zz.ccy.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;


public abstract class Erweima {

	public static void main(String[] args) throws NoSuchAlgorithmException, ParseException, IOException {

	/*		SendTextMsg t=new SendTextMsg();
			t.setTouser("openid");
			t.setMsgtype("text");
			JsonText te=new JsonText();
			te.setContent("nihao");
			t.setText(te);
			
			Gson j=new Gson();
			String json=j.toJson(t);
			System.out.println(json);*/
		
		getQRCode("d:/e.jpg","http://192.168.5.172/Delicious/page/sys");
		
		//Token t=new Token();
	/*	String old="2016-12-07 10:27:12";
		
		String newtime=getNowDate();
		//timec=timec.substring(10);
		System.out.println(old+"Сʱ"+newtime);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=sf.parse(old);
		Date d1=sf.parse(newtime);
		
		System.out.println(sf.format(d1.getTime()-7000000));
		System.out.println(d1.getTime()-d.getTime());*/
/*			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dt = sf.format(new Date());
			
		System.out.println(dt.substring(0, 10));*/
	
	}
	public static  void getQRCode(String path,String content) throws IOException{
		//����һ������
		Qrcode qrcode = new Qrcode();
		//���ö�ά��ľ�������
		//L 7% M 15% Q 25% H 30%
		qrcode.setQrcodeErrorCorrect('M');
		//�Զ����Ƶ���ʽ�洢
		qrcode.setQrcodeEncodeMode('B');
		//���ö�ά��汾 40 1=21*21 2=25*25
		qrcode.setQrcodeVersion(7);
		//�ַ�����
		 
		byte[] bt= new String(content.getBytes("ISO-8859-1"),"UTF-8").getBytes();
		//����һ��ͼ�����ݵĻ�����������һ��ֽ��
		BufferedImage image= new BufferedImage(140,140,BufferedImage.TYPE_INT_RGB);
		//����һ����
		Graphics2D g=image.createGraphics();
		//���ñ�����ɫΪ��ɫ
		g.setBackground(Color.white);
		//��䵽ֽ����
		g.fillRect(0, 0, 200, 200);
		//���ö�ά���ǰ��ɫ-��ɫ
		g.setColor(Color.green);
		//javac cavaj
		if(bt.length!=0){
		boolean[][] b= qrcode.calQrcode(bt);
		 for(int i=0;i<b.length;i++){
		for(int j=0;j<b.length;j++){
		if(b[j][i]){
		//���ݲ�����������ƶ�ά�����ͼ 
		g.fillRect(j*3 + 2, i*3 + 2, 3, 3);
		}   
		}
		 
		}
		}
		 
		File f =new File(path);
		ImageIO.write(image, "jpg",f); 
		 
		}
		
		
		
		
		
	
	public static String getNowDate() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dt = sf.format(new Date());
		return dt;
	}
}
