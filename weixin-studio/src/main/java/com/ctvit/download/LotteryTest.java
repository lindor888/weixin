package com.ctvit.download;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ctvit.bean.Probability;

public class LotteryTest {
	public static void main(String[] args) {
		Probability p = new Probability();
		p.setPrize_goodName("鼠标");
		p.setPrize_goodsCount(2);
		p.setPrize_goodsId(12);
		p.setPrize_goodsProbability(0.0002);
		p.setProbabilityNumber(100000 * 0.0002);
		p.setWaccountId("33");
		Probability p2 = new Probability();
		p2.setPrize_goodName("鼠标2");
		p2.setPrize_goodsCount(22);
		p2.setPrize_goodsId(12342);
		p2.setPrize_goodsProbability(0.0004);
		p2.setProbabilityNumber(100000 * 0.9);
		p2.setWaccountId("332");

		Random rand = new Random();
		int i = rand.nextInt(); // int范围类的随机数
		i = rand.nextInt(100000); // 生成0-100以内的随机数
		double j = (100000 * p.getPrize_goodsProbability());
		double t = (100000 * p2.getPrize_goodsProbability());
		List<Probability> list = new ArrayList<Probability>();
		list.add(p);
		list.add(p2);
		String result = "1";
		for (int y = 0; y < list.size(); y++) {// 概率数组循环

			int randomNum = new Random().nextInt(100000);// 随机生成1到sum的整数
			if (randomNum < list.get(y).getProbabilityNumber()) {// 中奖
				result = list.get(y).getPrize_goodName();
				break;
			} else {
				// sum -= obj[i];
			}

		}
		System.out.println(result);
		System.out.println(i);
		// i = (int)(Math.random() * 100); //0-100以内的随机数，用Matn.random()方式
	}
}
