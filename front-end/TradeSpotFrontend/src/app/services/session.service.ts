import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private sessionItems: any = {};

  private sessionItemSubject = new BehaviorSubject<any>({});
  sessionItems$ = this.sessionItemSubject.asObservable();

  public userRoleSubject = new BehaviorSubject<string | null>(null);
  userRole$ = this.userRoleSubject.asObservable();

  public userIdSubject = new BehaviorSubject<number>(null);
  userId$ = this.userIdSubject.asObservable();

  constructor() {
    let userCart = sessionStorage.getItem('user');
    if (userCart) {
      this.sessionItems = JSON.parse(userCart);
      this.sessionItemSubject.next(this.sessionItems);
      this.userRoleSubject.next(this.sessionItems.role);
      this.userIdSubject.next(this.sessionItems.id);
    }
  }

  getSessionItems(): any[] {
    return this.sessionItems; //for getting all elements of cartItems.
  }

  // private updateSessionStorageAndNotify() {
  //   sessionStorage.setItem('user', JSON.stringify(this.sessionItems));
  //   this.sessionItemSubject.next(this.sessionItems);
  // }

  removeSessionItems(): void {
    sessionStorage.clear();
    this.sessionItemSubject.next({});
    this.userRoleSubject.next(null);
  }

  getUserRole(): string | null {
    return this.userRoleSubject.value;
  }

  getUserId(): number | null {
    return this.userIdSubject.value;
  }
}
