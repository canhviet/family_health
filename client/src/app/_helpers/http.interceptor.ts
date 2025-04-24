import { Injectable } from '@angular/core';
import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
    HTTP_INTERCEPTORS,
    HttpResponse,
    HttpErrorResponse,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { AuthService } from '../_services/auth.service';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
    constructor(private authService: AuthService) { }

    intercept(
        req: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {


        const token = this.authService.getTokenData();

        if (token) {
            req = req.clone({
                withCredentials: true,
                setHeaders: {
                    Authorization: `Bearer ${token.accessToken}`,
                },
            });
        } else {
            req = req.clone({
                withCredentials: false,
            });
        }

        // Handle the request and inspect response/error
        return next.handle(req).pipe(
            tap((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {
                    console.log(
                        `Request to ${req.url} succeeded with status: ${event.status}`
                    );
                }
            }),
            catchError((error: HttpErrorResponse) => {
                const status = error.status;
                console.error(
                    `Request to ${req.url} failed with status: ${status}`
                );

                switch (status) {
                    case 403:
                        console.error(
                            '403 Forbidden: Access denied. Check token or permissions.'
                        );
                        // Optional: Add custom logic, e.g., redirect to login
                        break;
                    case 404:
                        console.error(
                            '404 Not Found: The requested resource does not exist.'
                        );
                        break;
                    case 401:
                        console.error(
                            '401 Unauthorized: Authentication required.'
                        );
                        // Optional: Redirect to login or refresh token
                        break;
                    case 500:
                        console.error(
                            '500 Server Error: Something went wrong on the server.'
                        );
                        break;
                    default:
                        console.error(`Unhandled status code: ${status}`);
                }

                return throwError(() => error);
            })
        );
    }
}

export const httpInterceptorProviders = [
    {
        provide: HTTP_INTERCEPTORS,
        useClass: HttpRequestInterceptor,
        multi: true,
    },
];
