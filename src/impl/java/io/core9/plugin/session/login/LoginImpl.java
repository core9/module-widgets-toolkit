package io.core9.plugin.session.login;


public class LoginImpl implements Login {

    private LoginRenderer renderer;


	public LoginImpl(String uuid) {

    }

	@Override
    public Login setRenderer(LoginRenderer renderer) {
		this.renderer = renderer;
		return this;
    }

	@Override
    public String toHtml() {
	    return renderer.render();
    }


}
