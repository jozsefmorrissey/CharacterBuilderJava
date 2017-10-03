package com.characterBuilder.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.characterBuilder.entities.UserMessage;
import com.characterBuilder.markers.HasDateTime;
import com.characterBuilder.markers.Message;

public class TestUtilities
{
	public static Random rnd = new Random();

	public static String getRandomString(int strLen) {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz"
        		+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        		+ "1234567890"
        		+ "          ";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < strLen) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	public static LocalDateTime getRandomLocalDateTime() {
		return LocalDateTime.now().minusNanos(rnd.nextLong());
	}
	
	public static int getInt() {
		return getInt(0, Integer.MAX_VALUE);
	}
	
	public static int getInt(int bound) {
		return getInt(0, bound);
	}
	
	public static int getInt(int start, int end)
	{
		return rnd.nextInt(end - start) + start;
	}
	
	public static boolean verifyMostReasentSaved(List<HasDateTime> all, List<HasDateTime> saved) {
		for(HasDateTime ahdt : all) {
			if(!saved.contains(ahdt)) {
				for(HasDateTime sHdt : saved) {
					if(ahdt.getDateTime().compareTo(sHdt.getDateTime()) < 0) {
						System.out.println(ahdt.getDateTime() + "\n" + sHdt.getDateTime());
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static void printArray(List<UserMessage> pardoned, String title) {
		System.out.println("\n\n" + title + ":");
		for(UserMessage msg : pardoned) {
			System.out.println(msg.getSenderId() + ":" + msg.getRecipientId() + ":" + msg.getDateTime());
		}
	}
	
	
	public static int allTrue(ArrayList<Boolean>bs)
	{
		return allSwitched(true, bs);
	}
	
	public static int allFalse(ArrayList<Boolean>bs)
	{
		return allSwitched(false, bs);
	}
	
	public static int allSwitched(boolean postion, ArrayList<Boolean>bs)
	{
		for(int i = 0; i < bs.size(); i++)
		{
			if(bs.get(i) != postion)
			{
				return i;
			}
		}
		return -1;
	}
	
	public static long findRecipients(List<Message> msgs, List<Boolean> found, int...recIds)
	{
		initializeList(found, recIds.length);
		for (Message msg : msgs)
		{
			long recId = msg.getRecipientId();
			if(recId != 0)
			{
				boolean discovered = false;
				for (int i = 0; i < recIds.length; i++)
				{
					int id = recIds[i];
					if(!found.get(i) && id == recId)
					{
						found.set(i, true);
						discovered = true;
					}
				}
				if(!discovered)
				{
					return recId;
				}
			}
		}
		return -1;
	}
	
	public static void initializeList(List<Boolean> bools, int size)
	{
		for(int i = 0; i < size; i++)
		{
			bools.add(false);
		}
	}
}









