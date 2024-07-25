import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';

declare var $: any; // Declare jQuery to avoid TypeScript errors

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {

  @ViewChild('sidebar', { static: false }) sidebar?: ElementRef<HTMLElement>;
  @ViewChild('sidebarOverlay', { static: false }) sidebarOverlay?: ElementRef<HTMLElement>;

  constructor(private renderer: Renderer2) { }

  ngAfterViewInit(): void {
    this.setupSidebar();
  }

  setupSidebar(): void {
    if (this.sidebar && this.sidebarOverlay) {
      $('#open-sidebar').click(() => {
        this.renderer.addClass(this.sidebar!.nativeElement, 'active');
        this.renderer.removeClass(this.sidebarOverlay!.nativeElement, 'd-none');
      });

      $('#sidebar-overlay').click(() => {
        this.renderer.removeClass(this.sidebar!.nativeElement, 'active');
        this.renderer.addClass(this.sidebarOverlay!.nativeElement, 'd-none');
      });
    } else {
      console.error('Sidebar or sidebarOverlay element is not available.');
    }
  }

}
