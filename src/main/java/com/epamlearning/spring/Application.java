package com.epamlearning.spring;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epamlearning.spring.aspect.CounterAspect;
import com.epamlearning.spring.config.ApplicationConfig;
import com.epamlearning.spring.config.AuditoriumConfig;
import com.epamlearning.spring.domain.Auditorium;
import com.epamlearning.spring.domain.Event;
import com.epamlearning.spring.domain.EventItem;
import com.epamlearning.spring.domain.Rating;
import com.epamlearning.spring.domain.User;
import com.epamlearning.spring.sevice.AuditoriumService;
import com.epamlearning.spring.sevice.BookingService;
import com.epamlearning.spring.sevice.DiscountService;
import com.epamlearning.spring.sevice.EventService;
import com.epamlearning.spring.sevice.UserService;


public class Application
{

	public static void main(final String[] args) throws Exception
	{
		final Logger logger = Logger.getLogger(CounterAspect.class);
		@SuppressWarnings("resource")
		final ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class, AuditoriumConfig.class);

		// some stuff just to check java config

		final UserService userService = ctx.getBean(UserService.class);
		final User user = userService.register("test1", "test1email");
		final EventService eventService = ctx.getBean(EventService.class);
		final Event event = eventService.create("TestEvent", (long) 300, Rating.AVERAGE);
		final AuditoriumService auditoriumService = ctx.getBean(AuditoriumService.class);
		final Auditorium auditorium = auditoriumService.getAuditoriumByName("First_Test_Palace");
		final EventItem show = eventService.assignAuditorium(event, auditorium, new DateTime(2016, 01, 01, 0, 0));
		final BookingService bookingService = ctx.getBean(BookingService.class);
		bookingService.bookTicket(user, show, 23);

		final DiscountService discountService = ctx.getBean(DiscountService.class);
		discountService.getDiscount(user, show);

		logger.debug(userService.getBookedTickets(user.getId()));
		logger.debug(eventService.getByName("TestEvent").getPrice());

	}
}
