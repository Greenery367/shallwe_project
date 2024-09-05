/**
 * 
 */let currentBanner = 0;
 const banners = document.querySelectorAll(`.banner`);
 const bannerCount = adminBanners.length;
 
 function showBanner(n) {
	adminBanners.forEach(banner => banner.style.display = 'none');
	adminBanners[n].style.display = 'block';
 }
 
 function nextBanner(){
	currentBanner = (currentBanner + 1)% bannerCount ;
	showBannaer(currentBanner);
 }
 
 function prevBanner(){
	currentBanner = (currentBanner - 1 + bannerCount) % bannerCount;
	showBanner(currentBanner);
 }
 
 document.addEventListener('DOMContentLoaded',()=>{
	showBanner(currentBanner);
	setInterval(nextBanner, 1000);
 })