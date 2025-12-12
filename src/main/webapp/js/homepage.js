let slideIndex = 0;
let slideInterval;
let isPlaying = true;

document.addEventListener("DOMContentLoaded", function () {
    showSlide(slideIndex);
    startAutoSlide();
});

function showSlide(index) {
    const slides = document.getElementsByClassName("banner-slide");
    const progressBars = document.querySelectorAll(".progress-loader .progress");

    if (index >= slides.length) slideIndex = 0;
    if (index < 0) slideIndex = slides.length - 1;

    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        progressBars[i].style.animation = "none"; // Stop all animations
        progressBars[i].style.width = "0"; // Reset width to 0 when slide changes
    }

    slides[slideIndex].style.display = "flex";

    // Start the progress animation for the active slide
    progressBars[slideIndex].style.animation = "loading 3s linear forwards"; // Change to forwards
}
function startAutoSlide() {
    slideInterval = setInterval(function () {
        slideIndex++;
        showSlide(slideIndex);
    }, 3000);
    isPlaying = true;
    updateProgressBars(); // Start progress animation when resuming
    document.getElementById("pausePlayBtn").innerHTML = '<i class="fas fa-pause"></i>'; // Update icon to pause
}

function stopAutoSlide() {
    clearInterval(slideInterval);
    document.getElementById("pausePlayBtn").innerHTML = '<i class="fas fa-play"></i>'; // Change icon to play
    isPlaying = false;

    // Keep the progress width at its current state
    const progressBars = document.querySelectorAll(".progress-loader .progress");
    for (let i = 0; i < progressBars.length; i++) {
        progressBars[i].style.animation = "none"; // Stop the animation
        // Optionally, keep the current width by not resetting it
    }
}


function updateProgressBars() {
    const progressBars = document.querySelectorAll(".progress-loader .progress");
    progressBars[slideIndex].style.animation = "loading 3s linear forwards"; // Resume animation
}

document.getElementById("pausePlayBtn").addEventListener("click", function () {
    if (isPlaying) {
        stopAutoSlide();
    } else {
        startAutoSlide();
    }
});

function plusSlides(n) {
    stopAutoSlide();
    slideIndex += n;
    showSlide(slideIndex);
    startAutoSlide();
}

function changeSlide(n) {
    clearInterval(slideInterval);
    slideIndex += n;
    showSlide(slideIndex);
    startAutoSlide();
}

document.querySelector(".prev-btn").addEventListener("click", function () {
    changeSlide(-1);
});
document.querySelector(".next-btn").addEventListener("click", function () {
    changeSlide(1);
});
const progressLoaders = document.querySelectorAll('.progress-loader');

progressLoaders.forEach((loader, index) => {
    const title = loader.getAttribute('data-title');

    const tooltip = document.createElement('div');
    tooltip.className = 'tooltip';
    tooltip.textContent = title;
    loader.appendChild(tooltip);

    loader.addEventListener('mouseover', function(event) {
        tooltip.style.display = 'block';
        tooltip.style.left = `${event.offsetX}px`;
        tooltip.style.top = `-30px`;
    });

    loader.addEventListener('mouseout', function() {
        tooltip.style.display = 'none';
    });

    loader.addEventListener('click', function() {
        stopAutoSlide();
        slideIndex = index;
        showSlide(slideIndex);
        startAutoSlide();
    });
});
// Thông báo lỗi khi chưa đăng nhập
let isLoggedIn = false;

document.addEventListener("DOMContentLoaded", function() {
    const myPage = document.getElementById("myPage");

    myPage.addEventListener("click", function(e) {
        if (!isLoggedIn) {
            e.preventDefault();
            alert("Vui lòng đăng nhập"); // << Giờ sẽ chạy
        } else {
            window.location.href = "profile.html";
        }
    });
});

//
const slider = document.querySelector('.blog-slider');
const prevBtn = document.querySelector('.prev-btn');
const nextBtn = document.querySelector('.next-btn');

nextBtn.addEventListener('click', () => {
    // Cuộn sang phải bằng chiều rộng của một thẻ + khoảng cách (250px + 25px)
    slider.scrollLeft += 275;
});

prevBtn.addEventListener('click', () => {
    // Cuộn sang trái
    slider.scrollLeft -= 275;
});