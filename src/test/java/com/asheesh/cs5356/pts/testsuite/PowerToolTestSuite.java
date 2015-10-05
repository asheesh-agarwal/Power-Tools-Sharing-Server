package com.asheesh.cs5356.pts.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.asheesh.cs5356.pts.testcase.PowerToolManagementTest;
import com.asheesh.cs5356.pts.testcase.UserLoginTest;
import com.asheesh.cs5356.pts.testcase.UserRegistrationTest;

@RunWith(Suite.class)
@SuiteClasses({ UserRegistrationTest.class, UserLoginTest.class, PowerToolManagementTest.class })

public class PowerToolTestSuite {
	// the class remains empty,
	// used only as a holder for the above annotations
}
