package com.movieApp.convertor;

import com.movieApp.entities.Show;
import com.movieApp.request.ShowRequest;

public class ShowConvertor {

	public static Show showDtoToShow(ShowRequest showRequest)
	{
		Show show = new Show();
		show.setTime(showRequest.getShowStartTime());
		show.setDate(showRequest.getShowDate());
		
		return show;
	}
}
