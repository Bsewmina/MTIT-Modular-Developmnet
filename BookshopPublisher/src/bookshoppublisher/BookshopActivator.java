package bookshoppublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class BookshopActivator implements BundleActivator {

	ServiceRegistration BookshopServiceRegistration;

	@Override
	public void start(BundleContext arg0) throws Exception {

		System.out.println("Bookshop publisher started ");
		BookshopPublisher bookshopPublisher = new BookshopPublisherImp();
		
		BookshopServiceRegistration = arg0.registerService(BookshopPublisher.class.getName(), bookshopPublisher, null);
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {

		System.out.println("Bookshop publisher stoped");
		BookshopServiceRegistration.unregister();
	}


}
