import service.ISportsBettingService;
import service.IView;
import service.SportsBettingService;
import service.View;

public class App {

	ISportsBettingService service;
	IView view;

	public App(ISportsBettingService service, IView view) {
		this.service = service;
		this.view = view;
	}

	public void play() {

	}

	public static void main(String[] args) {
		App app = new App(new SportsBettingService(), new View());
		app.play();
	}

	private void createPlayer() {

	}

	private void doBettings() {

	}

	private void calculateResults() {

	}

	private void printResults() {

	}

}
