package com.iOS.patient.testcases;

import com.affle.iOS.FrameWorkAssertion.FrameWorkSoftAssertions;
import com.affle.iOS.Listeners.AnnotationTransformers;
import com.affle.iOS.Listeners.ListenersClass;
import com.affle.iOS.frameWorkAnnotation.FrameWorkAnnotation;
import com.patient.screen.OrderToCashFlowScreen;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners({ListenersClass.class, AnnotationTransformers.class})
public class OrderToCashFlowTest extends BaseTest {
	FrameWorkSoftAssertions frameWorkSoftAssertions = new FrameWorkSoftAssertions();
	OrderToCashFlowScreen orderFlw;
	public void initialiseMethod() {
		orderFlw = new OrderToCashFlowScreen();
	}

	@FrameWorkAnnotation(TESTID = "Verify Product available")
	@Test(priority = 1)
	@Parameters({"label", "price"})
	public void verifyProductAvailable(@Optional String label, @Optional String price) {
		 initialiseMethod();
         frameWorkSoftAssertions.assertTrue(!orderFlw.captureProductDetails(label, price),"Given product not available");
	}

}
