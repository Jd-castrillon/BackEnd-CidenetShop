package com.cidenetshop.configuration.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cidenetshop.service.impl.UserDetailServiceImpl;

public class JwtTokenFilter extends OncePerRequestFilter {

	private final static Logger logger = org.slf4j.LoggerFactory.getLogger(JwtTokenFilter.class);

	private JwtProvider jwtProvider;

	private UserDetailServiceImpl userDetailService;
	
	
	
	@Autowired
	public JwtTokenFilter(UserDetailServiceImpl userDetailService) {
		this.userDetailService = userDetailService;
	}

	public JwtTokenFilter() {
		super();
	}

	@Autowired
	public JwtTokenFilter(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getToken(request);
			if (token != null && jwtProvider.validateToken(token)) {
				String userName = jwtProvider.getUsernameFromToken(token);
				UserDetails userDetails = userDetailService.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			logger.error("fail in the method doFilter" + e.getMessage());
		}
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer ", "");
		}
		return null;
	}

}
