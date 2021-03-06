window.onload = () => {
    const topButton = document.getElementById('moveTop');
    const spreadButton = document.querySelector('.spread');
    const spreadSpan = document.querySelector('.spread-span');
    const listNav = document.querySelector('.quick-list-nav');

    document.addEventListener('scroll', function () {
        if (document.documentElement.scrollTop === 0) {
            topButton.className = '';
        } else {
            topButton.className = 'on';
        }
    });

    topButton.addEventListener('click', function () {
        document.documentElement.scrollTop = 0;
    });

    spreadButton.addEventListener('click', function () {
        if (spreadSpan.className === 'spread-span' && listNav.className === 'quick-list-nav') {
            spreadSpan.className = 'active';
            listNav.className += ' active-list';
        } else {
            spreadSpan.className = 'spread-span';
            listNav.className = 'quick-list-nav';
        }
    });
}